import { useRouter } from "next/router"
import { ImageContainer, ItemContainer, ItemDetails } from "../../styles/pages/item"

export default function Item() {
    const { query } = useRouter()

    return (
      <ItemContainer>
        <ImageContainer>

        </ImageContainer>

        <ItemDetails>
            <h1>Gado 1</h1>
            <span>79,90</span>
            <p>Lorem ipsum, dolor sit amet consectetur adipisicing elit. Illo unde voluptate, alias fugit quia porro nemo quasi nulla eligendi! Dolorem totam eum facilis quasi et odit unde voluptates itaque ipsam.</p>
            <button>
                Order Now
            </button>
        </ItemDetails>
      </ItemContainer>
    )
  }
  