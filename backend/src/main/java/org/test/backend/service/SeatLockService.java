package org.test.backend.service;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBucket;
import org.redisson.api.RScript;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class SeatLockService {

    private static final String RELEASE_IF_OWNER =
            "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";

    private final RedissonClient redissonClient;
    private final Duration holdTtl;

    public SeatLockService(RedissonClient redissonClient,
                           @Value("${app.seat.hold-ttl:5m}") Duration holdTtl) {
        this.redissonClient = redissonClient;
        this.holdTtl = holdTtl;
    }

    public boolean lockSeat(UUID seanceId, Integer seatNumber, UUID userId) {
        RBucket<String> hold = bucket(seanceId, seatNumber);
        String ownerId = userId.toString();

        boolean acquired = hold.setIfAbsent(ownerId, holdTtl);
        if (acquired) {
            log.info("Seat held. seanceId={}, seatNumber={}, userId={}", seanceId, seatNumber, userId);
            return true;
        }

        boolean alreadyOwned = ownerId.equals(hold.get());
        if (alreadyOwned) {
            log.info("Seat already held by the same user. seanceId={}, seatNumber={}, userId={}", seanceId, seatNumber, userId);
            return true;
        }

        log.warn("Seat held by another user. seanceId={}, seatNumber={}", seanceId, seatNumber);
        return false;
    }

    public boolean releaseSeat(UUID seanceId, Integer seatNumber, UUID userId) {
        String key = key(seanceId, seatNumber);
        Long deleted = redissonClient.getScript(StringCodec.INSTANCE).eval(
                RScript.Mode.READ_WRITE,
                RELEASE_IF_OWNER,
                RScript.ReturnType.LONG,
                List.of(key),
                userId.toString()
        );

        boolean released = Long.valueOf(1L).equals(deleted);
        if (released) {
            log.info("Seat released. seanceId={}, seatNumber={}, userId={}", seanceId, seatNumber, userId);
        }
        return released;
    }

    private RBucket<String> bucket(UUID seanceId, Integer seatNumber) {
        return redissonClient.getBucket(key(seanceId, seatNumber), StringCodec.INSTANCE);
    }

    private String key(UUID seanceId, Integer seatNumber) {
        return "hold:seance:" + seanceId + ":seat:" + seatNumber;
    }
}
