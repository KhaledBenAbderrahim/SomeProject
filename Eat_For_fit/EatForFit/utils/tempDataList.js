export const zutaten = [
    {
        receipeID: '3757b9bf- b4cc - 49d4 - ac54 - 167708c13f89',
        image: require('../assets/Rezepte/kurbissuppe.png'),
        imageSmall: require('../assets/Rezepte/imagesSmall/kurbissuppe2.png'),
        name: 'Hokkaido Kürbissuppe',
        time: '30 min',
        beschreibung: 'Das Grundrezept kommt mit wenig Zutaten aus und lässt sich toll verfeinern.',
        gerichtstyp: ['Mittagessen'],
        categories: ['Suppe', 'Vegan'],
        data: [
            {
                id: '1',
                zutat: 'Hokkaido Kürbis',
                menge: 500,
                einheit: 'g',
                kcal: '150',
            },
            {
                id: '2',
                zutat: 'Karotten',
                menge: 250,
                einheit: 'g',
                kcal: '150',
            },
        ],
        schritt: [
            { //nice to have
                id: '1',
                text: 'Kürbis waschen, schneiden und im Wasser hinzufügen',
                data: [
                    { id: '7', zutat: 'Kürbis', menge: 500, einheit: 'g', },
                    { id: '8', zutat: 'Wasser', menge: 500, einheit: 'l', }
                ],
            },
            {
                id: '2',
                text: 'Karotten schälen und schnieden und im Wasser hinzufugen',
                data: [
                    { id: '9', zutat: 'Karotten', menge: 300, einheit: 'g', },
                ],
            },
            {
                id: '3',
                text: '3 Zwiebeln schalen und schneiden',
                data: [
                    { id: '10', zutat: 'Zwiebeln', menge: 100, einheit: 'g', },
                ],
            },
        ]
    },

    {
        receipeID: '3757b9bf- b4cc - 49d4 - ac54 - 167708c13f90',
        image: require('../assets/Rezepte/caprese.png'),
        imageSmall: require('../assets/Rezepte/imagesSmall/caprese2.png'),
        name: 'Caprese Salat',
        time: '30 min',
        beschreibung: 'Das Grundrezept kommt mit wenig Zutaten aus und lässt sich toll verfeinern.',
        gerichtstyp: ['Abendessen'],
        categories: ['Salate'],
        data: [
            {
                id: '3',
                zutat: 'Kirschtomaten',
                menge: 500,
                einheit: 'g',
                kcal: '150',
            },
            {
                id: '4',
                zutat: 'Mozzarella di Bufala',
                menge: 250,
                einheit: 'g',
                kcal: '150',
            },
        ],

        schritt: [
            { //nice to have
                id: '1',
                text: 'Die Tomaten waschen und schneiden',
                data: [
                    { id: '1', zutat: 'Tomaten', menge: 500, einheit: 'g', },
                    { id: '2', zutat: 'Basilico', menge: 10, einheit: 'g', }
                ],
            },
            {
                id: '2',
                text: 'Mozzarella in Scheiben schneiden',
                data: [
                    { id: '3', zutat: 'Mozzarella', menge: 100, einheit: 'g', },
                ],
            },
        ]
    },

    {
        receipeID: '3757b9bf- b4cc - 49d4 - ac54 - 167708c13f91',
        image: require('../assets/Rezepte/tomatoavocado.png'),
        imageSmall: require('../assets/Rezepte/imagesSmall/tomatoavocado2.png'),

        name: 'Avocado Salat',
        time: '30 min',
        beschreibung: 'Das Grundrezept kommt mit wenig Zutaten aus und lässt sich toll verfeinern.',
        gerichtstyp: ['Abendessen'],
        categories: ['Salate', 'Vegan'],
        data: [
            {
                id: '3',
                zutat: 'Avocado',
                menge: 500,
                einheit: 'g',
                kcal: '150',
            },
            {
                id: '4',
                zutat: 'Tomaten',
                menge: 250,
                einheit: 'g',
                kcal: '150',
            },
        ],
        
        schritt: [
            { //nice to have
                id: '1',
                text: 'Avocado schälen, schneiden und auf die Tortilla legen',
                data: [
                    { id: '4', zutat: 'Avocado', menge: 500, einheit: 'g', },
                    { id: '5', zutat: 'Tortllas', menge: 100, einheit: 'g', }
                ],
            },
            {
                id: '2',
                text: 'Tomaten waschen und schneiden schneiden',
                data: [
                    { id: '1', zutat: 'Tomaten', menge: 100, einheit: 'g', },
                ],
            },
            {
                id: '3',
                text: 'Eine Dose Mais öffnen und das Wasser entfernen',
                data: [
                    { id: '6', zutat: 'Mais', menge: 100, einheit: 'g', },
                ],
            },
        ]

    },
    {
        receipeID: '3757b9bf- b4cc - 49d4 - ac54 - 167708c13f93',
        image: require('../assets/Rezepte/pork.png'),
        imageSmall: require('../assets/Rezepte/imagesSmall/pork2.png'),
        name: 'Gebratene Schweinspieße mit Gemüse',
        time: '30 min',
        beschreibung: 'Das Grundrezept kommt mit wenig Zutaten aus und lässt sich toll verfeinern.',
        gerichtstyp: ['Abendessen', 'Mittagessen' ],
        categories: ['Schwein', 'Mittagessen', 'Abendessen'],
        data: [
            {
                id: '10',
                zutat: 'Fleisch vom Schwein',
                menge: 500,
                einheit: 'g',
                kcal: '150',
            },
            {
                id: '4',
                zutat: 'Tomaten',
                menge: 250,
                einheit: 'g',
                kcal: '150',
            },
        ],
        
        schritt: [
            { //nice to have
                id: '1',
                text: 'Avocado schälen, schneiden und auf die Tortilla legen',
                data: [
                    { id: '4', zutat: 'Avocado', menge: 500, einheit: 'g', },
                    { id: '5', zutat: 'Tortllas', menge: 100, einheit: 'g', }
                ],
            },
            {
                id: '2',
                text: 'Tomaten waschen und schneiden schneiden',
                data: [
                    { id: '1', zutat: 'Tomaten', menge: 100, einheit: 'g', },
                ],
            },
            {
                id: '3',
                text: 'Eine Dose Mais öffnen und das Wasser entfernen',
                data: [
                    { id: '6', zutat: 'Mais', menge: 100, einheit: 'g', },
                ],
            },
        ]

    },
    {
        receipeID: '3757b9bf- b4cc - 49d4 - ac54 - 167708c13f92',
        image: require('../assets/Rezepte/dessert3.png'),
        imageSmall: require('../assets/Rezepte/imagesSmall/dessert3.png'),

        name: 'Chia-Samen Porridge',
        time: '30 min',
        beschreibung: 'Das Grundrezept kommt mit wenig Zutaten aus und lässt sich toll verfeinern.',
        gerichtstyp: ['Frühstück'],
        categories: ['Dessert', 'Vegan'],
        data: [
            {
                id: '1',
                zutat: 'Chia-Samen',
                menge: 100,
                einheit: 'g',
                kcal: '150',
            },
            {
                id: '2',
                zutat: 'Mandel Milch',
                menge: 250,
                einheit: 'ml',
                kcal: '150',
            },
            {
                id: '3',
                zutat: 'Obst nach Wahl',
                menge: 100,
                einheit: 'g',
                kcal: '150',
            },
        ],
        
        schritt: [
            { //nice to have
                id: '1',
                text: 'Die Milch in ein verschließbares Glas gießen.',
                
                data: [
                    { id: '8', zutat: 'Mandel Milch', menge: 250, einheit: 'ml', }
                ],
            },
            {
                id: '2',
                text: 'Die Chia-Samen und das Süßungsmittel nach Wahl dazugeben, das Glas verschließen und einmal ordentlich durchschütteln.',
                data: [
                    { id: '7', zutat: 'Chia-Samen', menge: 100, einheit: 'g', },
                ],
            },
            {
                id: '3',
                text: 'Das Glas mit den Overnight Chias mindestens für 2 Stunden in den Kühlschrank stellen und alles aufquellen lassen.',
                data: [
                    { id: '7', zutat: 'Chia-Samen', menge: 100, einheit: 'g', },
                ],
            },
            {
                id: '4',
                text: 'Nach zwei Stunden den Glasinhalt einmal umrühren und das Ganze mit frischem Obst nach Wahl zusammen servieren.',
                data: [
                    { id: '7', zutat: 'Chia-Samen', menge: 100, einheit: 'g', },
                ],
            },
        ]

    }

]





export const gerichtstyp = [
    'Frühstück', 'Mittagessen', 'Abendessen'
]

export const categories = [
    {
        key: '1',
        name: 'Schwein',
        image: require('../assets/CategoriesImg/Schwein.png'),


    },
    {
        key: '2',
        name: 'Fisch',
        image: require('../assets/CategoriesImg/Fisch.png'),

    },
    {
        key: '3',
        name: 'Vegan',
        image: require('../assets/CategoriesImg/Vegan.png'),

    },
    {
        key: '4',
        name: 'Chicken',
        image: require('../assets/CategoriesImg/Chicken.png'),

    },
    {
        key: '5',
        name: 'Rind',
        image: require('../assets/CategoriesImg/Rind.png'),

    },
    {
        key: '6',
        name: 'Abendessen',
        image: require('../assets/CategoriesImg/Abendessen.png'),

    },
    {
        key: '7',
        name: 'Suppe',
        image: require('../assets/CategoriesImg/Suppe.png'),
    },
    {
        key: '8',
        name: 'Salate',
        image: require('../assets/CategoriesImg/Salate.png'),

    },
    {
        key: '9',
        name: 'Dessert',
        image: require('../assets/CategoriesImg/Dessert.png'),

    },
    {
        key: '10',
        name: 'Mittagessen',
        image: require('../assets/CategoriesImg/Mittagessen.png'),

    },
]

export const user =
{
    IDUser: 'edd8c2b6-b203-43a1-a2d8-a62393ccc178',
    userName: 'Max Mustermann',
}
