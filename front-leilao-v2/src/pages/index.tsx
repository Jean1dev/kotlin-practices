import Image from "next/image";
import { HomeContainer, HomeItem } from "../styles/pages/home";
import { useKeenSlider } from 'keen-slider/react'
import neloreImg from '../assets/nelore.jpg'
import 'keen-slider/keen-slider.min.css'
import Link from "next/link";

const Boi = () => {
  return (
    <Link href={'/item/2'}>
      <HomeItem
        className="keen-slider__slide">
        <Image src={neloreImg.src} width={520} height={480} alt={''} />

        <footer>
          <strong> Nelore</strong>
          <span>79,90</span>
        </footer>

      </HomeItem>
    </Link>)
}

const popula = () => {
  const arr = []
  for (let index = 0; index < 5; index++) {
    arr.push(Boi)

  }

  return arr
}

export default function Home() {
  const bois = popula()
  const [sliderRef] = useKeenSlider({
    slides: {
      perView: 3
    }
  })

  return (
    <HomeContainer ref={sliderRef} className="keen-slider">

      {bois.map((Item, index) => (
        <Item key={index} />
      ))}


    </HomeContainer>
  )
}
