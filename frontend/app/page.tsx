import MovieSlider from "@/components/MovieSlider";
import { fetcher } from "@/lib/fetcher";
import { getShowTimByDate } from "@/lib/getSliderData";

async function Home() {


  return (
    <MovieSlider/>
  )
}

export default Home;