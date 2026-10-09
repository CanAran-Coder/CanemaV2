import MovieSlider from "@/components/MovieSlider";
import Navbar from "@/components/Navbar";
import MovieCardList from "@/components/MovieCardList";

async function Home() {



  return (

    <>
      <Navbar />
      <MovieSlider />
      <MovieCardList />
    </>

  )
}

export default Home;