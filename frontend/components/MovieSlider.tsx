"use client";
import { FaClock } from "react-icons/fa6";

import { Autoplay, Navigation, Pagination } from "swiper/modules";
import { Swiper, SwiperSlide } from "swiper/react";
import type { MovieEntity } from "@/types/MovieEntity";
import { useState, useEffect } from "react";

import "swiper/css";
import "swiper/css/navigation";
import "swiper/css/pagination";
import { getMoviesByDate } from "@/lib/getSliderData";
import { toYearMonthDay } from "@/lib/toYearMonthDay";


export default function MovieSlider() {

  const [movies, setMovies] = useState<MovieEntity[]>([]);

  async function getMovies() {
    const date = toYearMonthDay(new Date());
    const movies = await getMoviesByDate(date);
    setMovies(movies);
  }

  useEffect(() => {
    getMovies();
  }, [])



  if (movies.length === 0) {
    return (
      <p className="px-4 py-8 text-sm text-neutral-400">There is no movie to show.</p>
    );
  }

  return (
    <div className="w-full h-screen relative">
      <Swiper
        spaceBetween={30}
        slidesPerView={1}
        autoplay={{
          delay: 5000,
          disableOnInteraction: false,
        }}
        loop={true}
        navigation={true}
        pagination={{
          clickable: true,
        }}
        modules={[Autoplay, Navigation, Pagination]}
        className="w-full h-full rounded-xl overflow-hidden"
      >
        {movies.map((movie) => (
          <SwiperSlide key={movie.id}>
            <div className="w-full h-full" style={{ backgroundImage: `url(${movie.imageUrl})`, backgroundSize: "cover", backgroundPosition: "center", backgroundRepeat: "no-repeat" }}>


              <div className="absolute inset-0 bg-gradient-to-r from-black/90 via-black/50 to-transparent z-10" />


              <div className="flex flex-col z-20 justify-center  pb-15 gap-y-4 items-start px-15  relative  h-full">

                <h1 className="text-8xl font-bold text-white ">{movie.name}</h1>
                <p className="text-xl text-neutral-200 tracking-tight leading-relaxed max-w-xl">{movie.description}</p>
                <div className="flex items-center gap-x-2">
                  <FaClock className="text-white font-black" />
                  <p className="text-xl text-neutral-200 tracking-tight leading-relaxed max-w-xl font-black">{movie.duration} Minutes</p>
                </div>
                <button className="bg-green-600 cursor-pointer text-white px-6 py-2 rounded-md font-black">Buy Ticket</button>

              </div>

            </div>

          </SwiperSlide>
        ))}
      </Swiper>
    </div>
  );
}
