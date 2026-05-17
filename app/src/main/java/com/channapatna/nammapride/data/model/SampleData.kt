package com.channapatna.nammapride.data.model

import com.channapatna.nammapride.data.local.entity.Artisan
import com.channapatna.nammapride.data.local.entity.Toy

object SampleData {
    val artisans = listOf(
        Artisan(
            "A001",
            "Master Ramaiah K.",
            "Craftsman Colony, Channapatna",
            12.6504,
            77.2068,
            38,
            "Traditional Lacquerware",
            "A national award winner, Master Ramaiah is a 4th generation artisan. His family has been keeping the 200-year-old Persian-inspired lacquerware tradition alive since the era of Tipu Sultan.",
            "" 
        ),
        Artisan(
            "A002",
            "Kavitha Devi",
            "Hale Channapatna, Karnataka",
            12.6521,
            77.2089,
            18,
            "Painted Wooden Figures",
            "Kavitha specialises in miniature storytelling. Her work depicts rural life using vibrant natural dyes extracted from turmeric, indigo, and pomegranate rinds.",
            "" 
        ),
        Artisan(
            "A003",
            "Suresh Gowda",
            "Industrial Estate, Channapatna",
            12.6487,
            77.2051,
            25,
            "Kinetic Spinning Toys",
            "Known as the 'Master of Balance', Suresh's spinning tops are famous for their mathematical precision, turned on hand-lathes passed down for over five generations.",
            "" 
        ),
        Artisan(
            "A004",
            "Meena Shankar",
            "Neelakantaswamy Layout, Channapatna",
            12.6512,
            77.2074,
            22,
            "Educational Toys",
            "Meena has pioneered organic learning toys. Her designs use non-toxic pigments and smooth, edge-free wood, making them safe for infants worldwide.",
            "" 
        ),
        Artisan(
            "A005",
            "Master Basavaraj M.",
            "Mandi Street, Channapatna",
            12.6540,
            77.2105,
            45,
            "Architectural Models",
            "Master Basavaraj creates complex structures using 'invisible joints', a secret method used by Channapatna masters since the 19th century.",
            ""
        )
    )

    val toys = listOf(
        Toy(
            "T001", "Prism Rainbow Top", "A003", "Ivory Wood, Lacquer",
            "A symbol of Channapatna for 200 years. Hand-turned by Suresh Gowda using traditional techniques and polished with dry palm leaves until it glows.",
            "https://m.media-amazon.com/images/I/71cat8vOQKL._SL1500_.jpg", 
            true, "NP-T001-VRF", "₹ 350", "Spinning Toys"
        ),
        Toy(
            "T002", "Gajapayana Elephant Set", "A001", "Rosewood, Vegetable Dyes",
            "Crafted by Master Ramaiah, these nesting elephants pay homage to the 200-year-old Mysore Dasara tradition.",
            "https://m.media-amazon.com/images/I/61Nl5F9e0vL._SL1100_.jpg", 
            true, "NP-T002-VRF", "₹ 1,200", "Heritage Collection"
        ),
        Toy(
            "T003", "Vedic Ganesha Figurine", "A002", "Sandalwood, Natural Resin",
            "Kavitha Devi's masterpiece. Carved following a 200-year-old organic recipe using turmeric and indigo dyes.",
            "https://m.media-amazon.com/images/I/71u9iW-20-L._SL1500_.jpg", 
            true, "NP-T003-VRF", "₹ 2,450", "Figurines"
        ),
        Toy(
            "T004", "Peacock Rattle (Organic)", "A004", "Rubberwood, Natural Lacquer",
            "Designed by Meena Shankar. This rattle is a modern revival of a centuries-old infant toy legacy, using non-toxic lac for child safety.",
            "https://m.media-amazon.com/images/I/61v-vD9o9TL._SL1500_.jpg", 
            true, "NP-T004-VRF", "₹ 180", "Rattles"
        ),
        Toy(
            "T005", "Rural Life Diorama", "A002", "Mixed Woods, Lacquer",
            "A complete 12-piece village scene by Kavitha Devi. Keeping the miniature storytelling tradition of Channapatna alive.",
            "https://m.media-amazon.com/images/I/61p-vD9o9TL._SL1500_.jpg", 
            true, "NP-T005-VRF", "₹ 3,800", "Heritage Collection"
        ),
        Toy(
            "T006", "Stacking Rings Tower", "A001", "Rubberwood, Non-toxic paint",
            "Classic stacking ring toy in seven colours, hand-turned by Master Ramaiah K. No sharp edges, suitable from 12 months.",
            "https://m.media-amazon.com/images/I/61y-vD9o9TL._SL1500_.jpg", 
            true, "NP-T006-VRF", "₹ 350", "Stacking Toys"
        ),
        Toy(
            "T007", "Traditional Dancing Doll", "A001", "Ivory Wood, Organic Polish",
            "The iconic symbol of Indian handicrafts. Reimagined by Master Ramaiah K. A miracle of physics perfected over 200 years.",
            "https://m.media-amazon.com/images/I/41D8Bv-qFHL._AC_UF894,1000_QL80_.jpg", 
            true, "NP-T007-VRF", "₹ 750", "Heritage Collection"
        ),
        Toy(
            "T008", "King's Lathe Chess Set", "A005", "Teak \u0026 Ivory Wood",
            "Master Basavaraj's signature chess set. Every piece is individually turned on a traditional lathe with precision accuracy.",
            "https://m.media-amazon.com/images/I/81vX8e-mDML._SL1500_.jpg",
            true, "NP-T008-VRF", "₹ 5,500", "Traditional Games"
        ),
        Toy(
            "T009", "Tactile Abacus", "A004", "Ivory Wood, Natural Pigments",
            "A tactile educational tool by Meena Shankar. The beads are friction-polished to a high-gloss finish using cactus-milk resin.",
            "https://m.media-amazon.com/images/I/71v-vD9o9TL._SL1500_.jpg",
            true, "NP-T009-VRF", "₹ 620", "Educational"
        ),
        Toy(
            "T010", "Wooden Whistle Set", "A003", "Light Wood",
            "Set of 3 whistles mimicking different bird calls, tuned perfectly by Suresh Gowda using traditional physics.",
            "https://m.media-amazon.com/images/I/61r5f75F4rL._SL1200_.jpg",
            true, "NP-T010-VRF", "₹ 240", "Musical"
        ),
        Toy(
            "T011", "Classic Train Pull-Along", "A001", "Mixed Woods",
            "A masterpiece of assembly by Master Ramaiah. Featuring a pull-along mechanism that has delighted children for generations.",
            "https://m.media-amazon.com/images/I/71Y7iV-m-NL._SL1500_.jpg",
            true, "NP-T011-VRF", "₹ 850", "Heritage Collection"
        ),
        Toy(
            "T012", "Bird Whistle (Robin)", "A003", "Light Wood",
            "Tuned perfectly by Suresh Gowda to mimic the call of the Indian Robin. A miracle of traditional acoustic physics.",
            "https://m.media-amazon.com/images/I/51r-vD9o9TL._SL1200_.jpg",
            true, "NP-T012-VRF", "₹ 150", "Musical"
        ),
        Toy(
            "T013", "Nesting Nesting Dolls", "A002", "Ivory Wood",
            "Kavitha Devi's intricate nesting dolls depicting the 5 seasons of Karnataka. Each doll fits perfectly inside the next.",
            "https://m.media-amazon.com/images/I/71N1u9iW-20-L._SL1500_.jpg",
            true, "NP-T013-VRF", "₹ 950", "Nesting Toys"
        ),
        Toy(
            "T014", "Solar System Mobile", "A003", "Light Wood, Glow Lacquer",
            "A hanging mobile for nursery rooms by Suresh Gowda. Uses a traditional bio-luminescent lacquer for a subtle glow.",
            "https://m.media-amazon.com/images/I/71u9iW-20-L._SL1500_.jpg",
            true, "NP-T014-VRF", "₹ 1,100", "Educational"
        ),
        Toy(
            "T015", "Village Bullock Cart", "A005", "Teak Wood",
            "Master Basavaraj's perfect scale model of a 19th-century transport. Features moving wheels and a rotating yoke.",
            "https://m.media-amazon.com/images/I/81Y7iV-m-NL._SL1500_.jpg",
            true, "NP-T015-VRF", "₹ 1,450", "Heritage Collection"
        )
    )

    fun getToyById(id: String) = toys.find { it.toyId == id || it.verificationCode == id }
    fun getArtisanById(id: String) = artisans.find { it.artisanId == id }
}
