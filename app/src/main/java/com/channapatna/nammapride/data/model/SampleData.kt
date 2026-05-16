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
            "A national award winner, Master Ramaiah is a 4th generation artisan. His workshop is a living museum where he preserves the ancient technique of using 'Aale Mara' (Ivory Wood) and organic lacquer derived from insect resin.",
            "https://images.unsplash.com/photo-1582213776825-069792e358c8?q=80&w=800"
        ),
        Artisan(
            "A002",
            "Kavitha Devi",
            "Hale Channapatna, Karnataka",
            12.6521,
            77.2089,
            18,
            "Fine Art Figurines",
            "Kavitha specialises in miniature storytelling through wood. Her work often depicts rural life in Karnataka, using vibrant natural dyes extracted from turmeric, indigo, and pomegranate rinds.",
            "https://images.unsplash.com/photo-1488421771513-36d50a633958?q=80&w=800"
        ),
        Artisan(
            "A003",
            "Suresh Gowda",
            "Industrial Estate, Channapatna",
            12.6487,
            77.2051,
            25,
            "Kinetic Spinning Toys",
            "Known as the 'Master of Balance', Suresh's spinning tops are famous for their perfectly centered gravity. He uses hand-powered lathes to ensure every curve is carved with mathematical precision.",
            "https://images.unsplash.com/photo-1507679799987-c73779587ccf?q=80&w=800"
        ),
        Artisan(
            "A004",
            "Meena Shankar",
            "Neelakantaswamy Layout, Channapatna",
            12.6512,
            77.2074,
            22,
            "Educational Montessori Toys",
            "Meena has pioneered the shift towards educational toys. Her 'Organic Learning' series uses non-toxic pigments and smooth, edge-free designs to make Channapatna toys safe for infants worldwide.",
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
            "Master Basavaraj is one of the few remaining artisans who can create complex architectural structures using lacquerware joints without a single drop of glue.",
            "https://images.unsplash.com/photo-1544168190-79c17527004f?q=80&w=800"
        ),
        Artisan(
            "A006",
            "Shanthi Kumar",
            "Silk City Extension, Channapatna",
            12.6465,
            77.2030,
            12,
            "Modern Home Decor",
            "Representing the new generation, Shanthi blends traditional lacquer techniques with contemporary Scandinavian designs, making Channapatna relevant for modern urban homes.",
            "https://images.unsplash.com/photo-1573496359142-b8d87734a5a2?q=80&w=800"
        ),
        Artisan(
            "A007",
            "Yusuf Ali",
            "Railway Station Road, Channapatna",
            12.6560,
            77.2120,
            30,
            "Musical Instruments",
            "Yusuf specialises in wooden rattles and small percussion instruments that are used in folk music across South India. His workshop is known for the musical 'clack' of his lathes.",
            "https://images.unsplash.com/photo-1506794778202-cad84cf45f1d?q=80&w=800"
        ),
        Artisan(
            "A008",
            "Lakshmi R.",
            "Temple Street, Channapatna",
            12.6495,
            77.2015,
            20,
            "Beaded Jewellery",
            "Lakshmi uses the discarded chips of ivory wood to create stunning, polished lacquer beads for necklaces and bangles, ensuring zero-waste in the craft process.",
            "https://images.unsplash.com/photo-1489424731084-a5d8b219a5bb?q=80&w=800"
        )
    )

    val toys = listOf(
        Toy(
            "T001", "Prism Rainbow Top", "A003", "Ivory Wood, Lacquer",
            "The classic Channapatna top, reimagined with a seven-color spectrum. Spun on a hand-lathe and polished with dry palm leaves.",
            "https://images.unsplash.com/photo-1515488042361-ee00e0ddd4e4?q=80&w=800",
            true, "NP-T001-VRF", "₹ 350", "Spinning Toys"
        ),
        Toy(
            "T002", "Gajapayana Elephant Set", "A001", "Rosewood, Vegetable Dyes",
            "A set of 5 nesting elephants representing the Mysore Dasara procession. Each piece is hand-carved over 48 hours.",
            "https://images.unsplash.com/photo-1558060308-410e3ca59322?q=80&w=800",
            true, "NP-T002-VRF", "₹ 1,200", "Heritage Collection"
        ),
        Toy(
            "T003", "Vedic Ganesha Figurine", "A002", "Sandalwood, Natural Resin",
            "Intricately carved deity figurine. The subtle aroma of sandalwood is preserved through a cold-carving process.",
            "https://images.unsplash.com/photo-1567591414240-e04746a78887?q=80&w=800",
            true, "NP-T003-VRF", "₹ 2,450", "Figurines"
        ),
        Toy(
            "T004", "Peacock Rattle (Organic)", "A004", "Ivory Wood, Natural Pigments",
            "Safe for teething infants. Filled with dried mung beans to create a soothing, natural sound.",
            "https://images.unsplash.com/photo-1596461404969-9ae70f2830c1?q=80&w=800",
            true, "NP-T004-VRF", "₹ 220", "Rattles"
        ),
        Toy(
            "T005", "Rural Life Diorama", "A002", "Mixed Woods, Lacquer",
            "A 12-piece set showcasing a Karnataka village. Includes a bullock cart, farmer, and traditional hut.",
            "https://images.unsplash.com/photo-1603775020644-eb8decd79994?q=80&w=800",
            true, "NP-T005-VRF", "₹ 3,800", "Art Collector"
        ),
        Toy(
            "T006", "Montessori Stacking Rings", "A004", "Ivory Wood, Non-toxic Paints",
            "7 rings of increasing size. Designed by Meena Shankar to improve motor skills and color recognition in toddlers.",
            "https://images.unsplash.com/photo-1587654780291-39c9404d746b?q=80&w=800",
            true, "NP-T006-VRF", "₹ 480", "Educational"
        ),
        Toy(
            "T007", "King's Chess Set", "A005", "Teak \u0026 Ivory Wood",
            "A premium chess set where every piece is turned on a lathe. Weighted bases for a professional feel.",
            "https://images.unsplash.com/photo-1529699211952-734e80c4d42b?q=80&w=800",
            true, "NP-T007-VRF", "₹ 5,500", "Traditional Games"
        ),
        Toy(
            "T008", "Modernist Bud Vase", "A006", "Ebony Wood, High-gloss Lacquer",
            "A sleek, minimalist vase that brings the Channapatna shine to modern interior design.",
            "https://images.unsplash.com/photo-1581783898377-1c85bf937427?q=80&w=800",
            true, "NP-T008-VRF", "₹ 850", "Home Decor"
        ),
        Toy(
            "T009", "Dancing Doll (Thanjavur Style)", "A001", "Ivory Wood, Organic Polish",
            "A gravity-defying doll that sways with the slightest breeze. The iconic symbol of Indian handicrafts.",
            "https://images.unsplash.com/photo-1518709268805-4e9042af9f23?q=80&w=800",
            true, "NP-T009-VRF", "₹ 750", "Heritage Collection"
        ),
        Toy(
            "T010", "Solar System Mobile", "A006", "Light Wood, Glow Lacquer",
            "A hanging mobile for nursery rooms. Uses a special rare bio-luminescent lacquer for a subtle glow at night.",
            "https://images.unsplash.com/photo-1550684848-fac1c5b4e853?q=80&w=800",
            true, "NP-T010-VRF", "₹ 1,100", "Educational"
        ),
        Toy(
            "T011", "Traditional Abacus", "A004", "Ivory Wood",
            "Multi-colored counting beads on a sturdy wooden frame. A staple for early mathematical development.",
            "https://images.unsplash.com/photo-1544377193-33dcf4d68fb5?q=80&w=800",
            true, "NP-T011-VRF", "₹ 620", "Educational"
        ),
        Toy(
            "T012", "Folk Drummer Rattle", "A007", "Mixed Woods",
            "A rhythmic toy that makes a sound as the drummer's hands hit the drum sides when shaken.",
            "https://images.unsplash.com/photo-1519892300165-cb5542fb47c7?q=80&w=800",
            true, "NP-T012-VRF", "₹ 290", "Musical"
        ),
        Toy(
            "T013", "Heritage Bangle Set", "A008", "Ivory Wood, Gold Foil",
            "A set of 4 bangles with intricate gold foil inlay over deep crimson lacquer. Wearable art.",
            "https://images.unsplash.com/photo-1535632066927-ab7c9ab60908?q=80&w=800",
            true, "NP-T013-VRF", "₹ 1,800", "Jewellery"
        ),
        Toy(
            "T014", "Zen Desktop Pebble", "A006", "Teak Wood",
            "Smooth, weighted wooden pebbles for stress relief and focus. Polished to a mirror finish.",
            "https://images.unsplash.com/photo-1560419015-7c427e8ae5ba?q=80&w=800",
            true, "NP-T014-VRF", "₹ 450", "Home Decor"
        ),
        Toy(
            "T015", "Pull-along Engine", "A001", "Ivory Wood",
            "A robust train engine with rotating wheels. The most gifted toy for toddlers in Channapatna.",
            "https://images.unsplash.com/photo-1596461404969-9ae70f2830c1?q=80&w=800",
            true, "NP-T015-VRF", "₹ 550", "Vehicle Toys"
        )
    )

    fun getToyById(id: String) = toys.find { it.toyId == id || it.verificationCode == id }
    fun getArtisanById(id: String) = artisans.find { it.artisanId == id }
}
