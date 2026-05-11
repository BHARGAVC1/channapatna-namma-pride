package com.channapatna.nammapride.data.model

import com.channapatna.nammapride.data.local.entity.Artisan
import com.channapatna.nammapride.data.local.entity.Toy

object SampleData {
    val artisans = listOf(
        Artisan(
            "A001",
            "Ramaiah K.",
            "Channapatna, Karnataka",
            12.6504,
            77.2068,
            28,
            "Lacquerware Wooden Toys",
            "Third-generation craftsman specialising in traditional Channapatna lacquerware. Trained under his father at age 10, Ramaiah now trains over 20 apprentices annually.",
            ""
        ),
        Artisan(
            "A002",
            "Kavitha Devi",
            "Channapatna, Karnataka",
            12.6521,
            77.2089,
            15,
            "Painted Wooden Figures",
            "Kavitha specialises in hand-painted figurines depicting scenes from Indian mythology. Her use of natural dyes derived from plant extracts sets her work apart.",
            ""
        ),
        Artisan(
            "A003",
            "Suresh Gowda",
            "Channapatna, Karnataka",
            12.6487,
            77.2051,
            22,
            "Spinning Tops & Rattles",
            "Suresh is known across Karnataka for perfectly balanced spinning tops turned on hand-powered lathes. Each top is individually weighted and tested before sale.",
            ""
        ),
        Artisan(
            "A004",
            "Meena Shankar",
            "Channapatna, Karnataka",
            12.6512,
            77.2074,
            18,
            "Animal & Bird Figurines",
            "Meena carves and paints miniature animal and bird figurines inspired by the wildlife of Karnataka. She sources all wood from sustainably managed rosewood plantations.",
            ""
        )
    )

    val toys = listOf(
        Toy(
            "T001",
            "Rainbow Spinning Top",
            "A003",
            "Indian Rosewood, Natural lacquer",
            "Hand-turned on a traditional lathe, coated with seven layers of natural plant-based lacquer, each layer polished before the next is applied. Requires 3 days to complete.",
            "",
            true,
            "NP-T001-VRF",
            "₹ 280",
            "Spinning Toys"
        ),
        Toy(
            "T002",
            "Nesting Elephant Set",
            "A001",
            "Rubberwood, Non-toxic acrylic paint",
            "Set of five nesting elephants, each turned on a lathe and painted with traditional Karnataka motifs. Safe for children above 18 months.",
            "",
            true,
            "NP-T002-VRF",
            "₹ 650",
            "Nesting Toys"
        ),
        Toy(
            "T003",
            "Lord Ganesha Figurine",
            "A002",
            "Sandalwood, Natural plant-based dyes",
            "Hand-carved from a single piece of sandalwood, painted with pigments extracted from turmeric, indigo, and pomegranate rind. Each piece is unique.",
            "",
            true,
            "NP-T003-VRF",
            "₹ 1200",
            "Figurines"
        ),
        Toy(
            "T004",
            "Peacock Rattle",
            "A004",
            "Rubberwood, Natural lacquer, dried seeds",
            "Peacock-shaped rattle filled with dried mung seeds for a gentle sound. Painted with traditional peacock motifs using mineral pigments. BIS certified safe.",
            "",
            true,
            "NP-T004-VRF",
            "₹ 180",
            "Rattles"
        ),
        Toy(
            "T005",
            "Village Scene Diorama",
            "A002",
            "Rosewood, Natural pigments, brass fittings",
            "A miniature Karnataka village scene with farmer, bullock cart, and hut — all hand-carved and painted. Takes approximately 5 days to produce one set.",
            "",
            true,
            "NP-T005-VRF",
            "₹ 2400",
            "Figurines"
        ),
        Toy(
            "T006",
            "Stacking Rings Tower",
            "A001",
            "Rubberwood, Non-toxic water-based paint",
            "Classic stacking ring toy in seven colours, each ring turned on a lathe and sanded to a smooth finish. No sharp edges. Suitable from 12 months.",
            "",
            true,
            "NP-T006-VRF",
            "₹ 350",
            "Stacking Toys"
        )
    )

    fun getToyById(id: String) = toys.find { it.toyId == id || it.verificationCode == id }
    fun getArtisanById(id: String) = artisans.find { it.artisanId == id }
}
