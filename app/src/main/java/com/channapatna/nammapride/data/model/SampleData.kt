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
            "https://images.unsplash.com/photo-1540910419892-f0c97a2240ef?q=80&w=800"
        ),
        Artisan(
            "A002",
            "Kavitha Devi",
            "Hale Channapatna, Karnataka",
            12.6521,
            77.2089,
            18,
            "Fine Art Figurines",
            "Kavitha specialises in miniature storytelling. Her work depicts rural life using vibrant natural dyes extracted from turmeric, indigo, and pomegranate rinds.",
            "https://images.unsplash.com/photo-1542332213-9b5a5a3fab35?q=80&w=800"
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
            "https://images.unsplash.com/photo-1506794778202-cad84cf45f1d?q=80&w=800"
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
            "https://images.unsplash.com/photo-1594744803329-e58b31de8bf5?q=80&w=800"
        ),
        Artisan(
            "A005",
            "Basavaraj M.",
            "Mandi Street, Channapatna",
            12.6540,
            77.2105,
            45,
            "Architectural Models",
            "Master Basavaraj creates complex structures using 'invisible joints', a secret method used by Channapatna masters since the 19th century.",
            "https://images.unsplash.com/photo-1552345386-24027793e513?q=80&w=800"
        ),
        Artisan(
            "A006",
            "Shanthi Kumar",
            "Silk City Extension, Channapatna",
            12.6465,
            77.2030,
            12,
            "Modern Home Decor",
            "Shanthi blends traditional lacquer techniques with contemporary designs, making Channapatna relevant for modern urban homes.",
            "https://images.unsplash.com/photo-1589156280159-27698a70f29e?q=80&w=800"
        ),
        Artisan(
            "A007",
            "Yusuf Ali",
            "Railway Station Road, Channapatna",
            12.6560,
            77.2120,
            30,
            "Musical Instruments",
            "Yusuf specialises in wooden rattles and percussion instruments used in folk music. His workshop is known for its musical heritage.",
            "https://images.unsplash.com/photo-1589156191108-c762ff4b96ab?q=80&w=800"
        ),
        Artisan(
            "A008",
            "Lakshmi R.",
            "Temple Street, Channapatna",
            12.6495,
            77.2015,
            20,
            "Beaded Jewellery",
            "Lakshmi turns wood waste into wearable art, using the same friction-polishing techniques that historically adorned royal jewelry.",
            "https://images.unsplash.com/photo-1621252178225-b44e66289069?q=80&w=800"
        ),
        Artisan(
            "A009",
            "Govinda Raju",
            "Fort Area, Channapatna",
            12.6580,
            77.2150,
            33,
            "Traditional Dolls",
            "Govinda is a master of Raja-Rani doll sets, documented since the 1700s. He is one of the few who still knows the ancient patterns.",
            "https://images.unsplash.com/photo-1558449028-b53a39d100fc?q=80&w=800"
        ),
        Artisan(
            "A010",
            "Rathnamma S.",
            "Channapatna Old Town",
            12.6450,
            77.1980,
            27,
            "Organic Crafts",
            "Rathnamma leads an all-women cooperative that produces high-end organic wooden items using cactus-milk resin for natural shine.",
            "https://images.unsplash.com/photo-1614705827065-69c82110bb5e?q=80&w=800"
        )
    )

    val toys = listOf(
        Toy(
            "T001", "Prism Rainbow Top", "A003", "Ivory Wood, Lacquer",
            "A symbol of Channapatna for 200 years. Hand-turned by Suresh Gowda using traditional techniques and polished with dry palm leaves until it glows.",
            "https://images.unsplash.com/photo-1515488042361-ee00e0ddd4e4?q=80&w=800",
            true, "NP-T001-VRF", "₹ 350", "Spinning Toys"
        ),
        Toy(
            "T002", "Gajapayana Elephant Set", "A001", "Rosewood, Vegetable Dyes",
            "Crafted by Master Ramaiah, these nesting elephants pay homage to the 200-year-old Mysore Dasara tradition.",
            "https://images.unsplash.com/photo-1544967082-d9d25d867d66?q=80&w=800",
            true, "NP-T002-VRF", "₹ 1,200", "Heritage Collection"
        ),
        Toy(
            "T003", "Vedic Ganesha Figurine", "A002", "Sandalwood, Natural Resin",
            "Kavitha Devi's masterpiece. Carved following a 200-year-old organic recipe using turmeric and indigo dyes.",
            "https://images.unsplash.com/photo-1543051932-6ef9fecfbc80?q=80&w=800",
            true, "NP-T003-VRF", "₹ 2,450", "Figurines"
        ),
        Toy(
            "T004", "Peacock Rattle (Organic)", "A004", "Ivory Wood, Natural Pigments",
            "Designed by Meena Shankar. This rattle is a modern revival of a centuries-old infant toy legacy.",
            "https://images.unsplash.com/photo-1596461404969-9ae70f2830c1?q=80&w=800",
            true, "NP-T004-VRF", "₹ 220", "Rattles"
        ),
        Toy(
            "T005", "Rural Life Diorama", "A002", "Mixed Woods, Lacquer",
            "A complete 12-piece village scene by Kavitha Devi. Keeping the miniature storytelling tradition of Channapatna alive for over two centuries.",
            "https://images.unsplash.com/photo-1502086223501-7ea6ecd79368?q=80&w=800",
            true, "NP-T005-VRF", "₹ 3,800", "Art Collector"
        ),
        Toy(
            "T006", "Montessori Stacking Rings", "A004", "Ivory Wood, Non-toxic Paints",
            "Meena Shankar's vibrant stacker. Uses wood-turning skills that date back to the Persian fusion era of the 1800s.",
            "https://images.unsplash.com/photo-1587654780291-39c9404d746b?q=80&w=800",
            true, "NP-T006-VRF", "₹ 480", "Educational"
        ),
        Toy(
            "T007", "King's Lathe Chess Set", "A005", "Teak \u0026 Ivory Wood",
            "Master Basavaraj's premium chess set. Every piece is turned on a lathe with precision developed over 45 years of mastering the 200-year-old craft.",
            "https://images.unsplash.com/photo-1529699211952-734e80c4d42b?q=80&w=800",
            true, "NP-T007-VRF", "₹ 5,500", "Traditional Games"
        ),
        Toy(
            "T008", "Modernist Bud Vase", "A006", "Ebony Wood, High-gloss Lacquer",
            "Shanthi Kumar's contemporary take. Achieving the deep mirror shine of ancient lacquerware for the modern home.",
            "https://images.unsplash.com/photo-1581783898377-1c85bf937427?q=80&w=800",
            true, "NP-T008-VRF", "₹ 850", "Home Decor"
        ),
        Toy(
            "T009", "Traditional Dancing Doll", "A001", "Ivory Wood, Organic Polish",
            "The iconic symbol of Indian handicrafts, reimagined by Master Ramaiah. A miracle of physics perfected over 200 years.",
            "https://images.unsplash.com/photo-1566492031773-4f4e44671857?q=80&w=800",
            true, "NP-T009-VRF", "₹ 750", "Heritage Collection"
        ),
        Toy(
            "T010", "Heritage Bangle Set", "A008", "Ivory Wood, Gold Foil",
            "Lakshmi R.'s jewelry. Polished to a mirror finish using cactus-milk resin, a technique used for generations.",
            "https://images.unsplash.com/photo-1621252178225-b44e66289069?q=80&w=800",
            true, "NP-T010-VRF", "₹ 1,800", "Jewellery"
        ),
        Toy(
            "T011", "Traditional Abacus", "A004", "Ivory Wood",
            "Meena Shankar's educational abacus. Every bead is hand-turned using traditional methods to make learning tactile and beautiful.",
            "https://images.unsplash.com/photo-1544377193-33dcf4d68fb5?q=80&w=800",
            true, "NP-T011-VRF", "₹ 620", "Educational"
        ),
        Toy(
            "T012", "Folk Drummer Rattle", "A007", "Mixed Woods",
            "Yusuf Ali's rhythmic toy. Follows the Persian wood-turning heritage introduced over two centuries ago.",
            "https://images.unsplash.com/photo-1519892300165-cb5542fb47c7?q=80&w=800",
            true, "NP-T012-VRF", "₹ 290", "Musical"
        ),
        Toy(
            "T013", "Emperor's Palla Box", "A005", "Teak Wood",
            "Master Basavaraj's nested storage box. Featuring a secret rotating lock mechanism used since the 19th century.",
            "https://images.unsplash.com/photo-1549490349-8643362247b5?q=80&w=800",
            true, "NP-T013-VRF", "₹ 1,550", "Home Decor"
        ),
        Toy(
            "T014", "Bird-call Whistle Set", "A007", "Light Wood",
            "Set of bird whistles by Yusuf Ali. Tuned to mimic the wildlife of the Deccan plateau using traditional physics.",
            "https://images.unsplash.com/photo-1550745165-9bc0b252726f?q=80&w=800",
            true, "NP-T014-VRF", "₹ 240", "Musical"
        ),
        Toy(
            "T015", "Village Bullock Cart", "A009", "Rosewood",
            "Govinda Raju's masterpiece. A perfect scale model of the primary transport of 18th-century Channapatna.",
            "https://images.unsplash.com/photo-1582213776825-069792e358c8?q=80&w=800",
            true, "NP-T015-VRF", "₹ 950", "Heritage Collection"
        )
    )

    fun getToyById(id: String) = toys.find { it.toyId == id || it.verificationCode == id }
    fun getArtisanById(id: String) = artisans.find { it.artisanId == id }
}
