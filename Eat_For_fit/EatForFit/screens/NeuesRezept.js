import React, { useContext, useState } from 'react';
import { TextInput, TouchableOpacity, StyleSheet, View, Text, Picker, Image } from 'react-native';
import { categories } from '../utils/tempDataList';
import { gerichtstyp } from '../utils/tempDataList';
import { Feather } from '@expo/vector-icons';
import * as ImagePicker from 'expo-image-picker';

export default function NeuesRezept({ route, navigation }) {
    const pickImage = async () => {
        let result = await ImagePicker.launchImageLibraryAsync({
            mediaTypes: ImagePicker.MediaTypeOptions.All,
            allowsEditing: true,
            aspect: [4, 3],
            quality: 1,
        });

        console.log(result);

        if (!result.cancelled) {
            setNewPhoto(result.uri);
        }
    };

    const [data, setData] = useState([]);
    const [schrittListe, setSchrittListe] = useState([]);


    const [gericht, setGericht] = useState("neues Gericht");
    const [gerichtstypNeues, setGerichtstypNeues] = useState("neues Gerichtstyp");
    const [categoriesNeues, setCategoriesNeues] = useState("neue Categoriy");
    const [zutat, setZutat] = useState("neue Zutat");
    const [menge, setMenge] = useState("neue Menge");
    const [einheit, setEinheit] = useState("neue Einheit1");
    const [zutat1, setZutat1] = useState("neue Zutat1");
    const [menge1, setMenge1] = useState("neue Menge1");
    const [einheit1, setEinheit1] = useState("neue Einheit");
    const [zeit, setZeit] = useState("neue Vorbereitungszeit");
    const [beschreibung1, setBeschreibung1] = useState("neue Beschreibung1");
    const [beschreibung2, setBeschreibung2] = useState("neue Beschreibung2");
    const [schrittId, setSchrittId] = useState(1);
    const [schritt, setSchritt] = useState([]);
    const [newPhoto, setNewPhoto] = useState("leer");

    return (

        <View style={styles.container}>
            <Text>  </Text>
            <Text>Aktuelle Zutat ist: {zutat}</Text>
            {/* <Text>Gerichtstyp: {gerichtstypNeues}</Text>
            <Text>Category: {categoriesNeues}</Text>  
            <Text>B1: {beschreibung1}</Text>        */}
            <Text>Wie viele zutaten: {data.length}</Text>
            <Text>Zutaten in JSON: {JSON.stringify(data)}</Text>

            <Text>Wie viele schritten: {schritt.length}</Text>
            <Text>Schritten in JSON: {JSON.stringify(schritt)}</Text>

            <Image
                source={{ uri: newPhoto }}
                style={{ width: 50, height: 50, }}
            />

            {/* <Text>Aktuelle Zutatenliste ist: {zutatenListe}</Text>  */}

            <View style={styles.boxStyle}>
                <View style={{ flexDirection: "row", height: 60 }}>
                    <TextInput
                        style={styles.textInput4}
                        placeholder=" Name des Gerichts"
                        onSubmitEditing={event => {
                            console.log('Gericht ist: ' + event.nativeEvent.text)
                            setGericht(event.nativeEvent.text)
                         
                        }}
                    />
                    <TouchableOpacity 
                    onSubmitEditing={event => {
                    setNewPhoto(event.nativeEvent.text)}}
                    onPress={pickImage}>
                        <Feather name="camera" size={60} color="grey" />
                    </TouchableOpacity>
                </View>
                <TextInput
                    style={styles.textInput}
                    placeholder=" Rezept Beschreibung"
                    onSubmitEditing={event => {
                        // console.log("B ist: " +event.nativeEvent.text)
                        setBeschreibung1(event.nativeEvent.text)
                    }}
                />
                <TextInput
                    style={styles.textInput}
                    placeholder=" Vorbereitungszeit (Minuten)"
                    onSubmitEditing={event => {
                        console.log('Die Zubereitungszeit ist: ' + event.nativeEvent.text)
                        setZeit(event.nativeEvent.text)
                    }}
                />
            </View>

            <View style={styles.boxStyle}>
                <View style={styles.pickerStyle}>
                    <Picker
                        mode="dropdown"

                        onValueChange={(value) => {
                            console.log("Gerichtstyp: " +value)
                            setGerichtstypNeues(value); }}>

                        {gerichtstyp.map((item, index) => {
                            return (<Picker.Item label={item} value={item} key={index} />)
                        })}
                    </Picker>
                </View>

                <View style={styles.pickerStyle}>
                    <Picker
                        mode="dropdown"
                        onValueChange={(value) => {
                            console.log("Category: " +value)
                            setCategoriesNeues(value); }}>
                                
                        {categories.map((item, index) => {
                            return (<Picker.Item label={item.name} value={item.name} key={index} />)
                        })}
                    </Picker>
                </View>

            </View>

            <View style={styles.boxStyle}>
                <View style={{ flexDirection: "row", height: 60 }}>
                    <TextInput
                        style={styles.textInput2}
                        placeholder=" Zutat"
                        onSubmitEditing={event => {
                            console.log('Zutat ist: ' + event.nativeEvent.text)
                            setZutat(event.nativeEvent.text)
                        }}
                    />
           
                    <TextInput
                        style={styles.textInput3}
                        placeholder=" Menge"
                        onSubmitEditing={event => {
                            console.log('Menge ist: ' + event.nativeEvent.text)
                            setMenge(event.nativeEvent.text)
                        }}
                    />
                    {/* <Text style={styles.text1}>Einheit: </Text> */}
                    <TextInput
                        style={styles.textInput3}
                        placeholder=" Einheit"
                        onSubmitEditing={event => {
                            console.log('Einheit ist: ' + event.nativeEvent.text)
                            setEinheit(event.nativeEvent.text)
                        }}
                    />
                </View>
                <TouchableOpacity
                    style={styles.button}
                    onPress={() => {
                        var data2 = data.slice();

                        data2.push({ zutat: zutat, menge: menge, einheit: einheit })
                        setData(data2)
                    }}
                >
                    <Text style={styles.text2}>ZUTAT HINZUFUGEN</Text>
                </TouchableOpacity>
            </View>
            <Text style={styles.text1}>Zubereitung: </Text>
            {/* <Text style={styles.text1}>Schritt: </Text> */}

            <View style={styles.boxStyle}>
                <View style={{ flexDirection: "row", height: 60 }}>
                    <TextInput
                        style={styles.textInput3}
                        placeholder=" Schritt 1"
                        onSubmitEditing={event => {
                        setSchrittId(event.nativeEvent.text)
                        }}
                    />
                    <TextInput
                        style={styles.textInput5}
                        placeholder=" Schritt Beschreibung"
                        onSubmitEditing={event => {
                            setBeschreibung2(event.nativeEvent.text)
                        }}
                    />
                </View>

                <View style={{ flexDirection: "row", height: 60 }}>
                    <TextInput
                        style={styles.textInput2}
                        placeholder=" Zutat"
                        onSubmitEditing={event => {
                            console.log('Zutat ist: ' + event.nativeEvent.text)
                            setZutat1(event.nativeEvent.text)
                        }}
                    />
                    {/* <Text style={styles.text1}>Menge: </Text> */}
                    <TextInput
                        style={styles.textInput3}
                        placeholder=" Menge"
                        onSubmitEditing={event => {
                            console.log('Menge ist: ' + event.nativeEvent.text)
                            setMenge1(event.nativeEvent.text)
                        }}
                    />
                    {/* <Text style={styles.text1}>Einheit: </Text> */}
                    <TextInput
                        style={styles.textInput3}
                        placeholder=" Einheit"
                        onSubmitEditing={event => {
                            console.log('Einheit ist: ' + event.nativeEvent.text)
                            setEinheit1(event.nativeEvent.text)
                        }}
                    />
                </View>



                <TouchableOpacity
                    style={styles.button}
                    onPress={() => {
                        var schritt2 = schritt.slice();

                        console.log("id: " +schrittId)
                        console.log("beschreibung: " +beschreibung2)
                        console.log("zutat: " +zutat1)
                        console.log("menge: " +menge1)
                        console.log("einheit: " +einheit1)

                        schritt2.push({ id: schrittId, beschreibung: beschreibung2, zutat: zutat1, menge: menge1, einheit: einheit1 })
                        setSchritt(schritt2)
                        
                    }}
                >
                    <Text style={styles.text2}>SCHRITT HINZUFUGEN</Text>
                </TouchableOpacity>

            </View>

            <TouchableOpacity
                style={styles.button}
                onPress={() => {

                    console.log("Going to Home")

                    console.log("Gericht name ist: " +gericht)
                    console.log("Gericht Beschreibung: " +beschreibung1)
                    console.log("Updated Zutatenliste: " +JSON.stringify(data))

                    console.log("Updated Gerichtstip: " +gerichtstypNeues)
                    console.log("Updated Category: " +categories)
                    console.log("Updated Photo: " +newPhoto)
                    console.log("Updated Schritt Liste: " +schritt)

                    navigation.navigate("Home", {
                        name: gericht,
                        time: zeit,
                        receipeId: "1", 
                        image: newPhoto,
                        imageSmall: newPhoto,
                        beschreibung: beschreibung1,
                        gerichtstyp: [ gerichtstypNeues ],
                        categories: [ categories ],
                        data: data,
                        schritt: schritt } )
                }}
            >
                <Text style={styles.text2}>REZEPT SPEICHERN</Text>
            </TouchableOpacity>
        </View>
    );

}

const styles = StyleSheet.create({
    container: {
        //flex: 1,
        color: 'white',
        alignItems: 'center',
        justifyContent: 'center',
    },
    alignedInARow: {
        //flex: 1,
        flexDirection: 'row',
        alignItems: 'center',
        justifyContent: 'center',
        paddingVertical: 1,
    },
    image: {
        width: 30,
        height: 30
    },

    button: {
        backgroundColor: 'salmon',
        alignItems: 'center',
        justifyContent: 'center',
        paddingHorizontal: 20,
        paddingVertical: 10,
        width: 140,
        margin: 20,
        borderRadius: 8,
        borderWidth: 1,
        borderColor: 'salmon'

    },

    textInput: {
        color: 'black',
        fontSize: 18,
        alignItems: 'center',
        justifyContent: 'center',
        paddingHorizontal: 1,
        paddingVertical: 12,
        width: 400,
        margin: 3,
        borderRadius: 0,
        borderWidth: 2,
        borderColor: 'black'

    },
    textInput2: {
        color: 'black',
        fontSize: 18,
        alignItems: 'center',
        justifyContent: 'center',
        paddingHorizontal: 1,
        paddingVertical: 12,
        width: 190,
        margin: 3,
        borderRadius: 0,
        borderWidth: 2,
        borderColor: 'black'

    },
    textInput3: {
        color: 'black',
        fontSize: 18,
        alignItems: 'center',
        justifyContent: 'center',
        paddingHorizontal: 1,
        paddingVertical: 12,
        width: 100,
        margin: 3,
        borderRadius: 0,
        borderWidth: 2,
        borderColor: 'black'

    },

    textInput4: {
        color: 'black',
        fontSize: 18,
        alignItems: 'center',
        justifyContent: 'center',
        paddingHorizontal: 1,
        paddingVertical: 12,
        width: 336,
        margin: 3,
        marginRight: 8,
        borderRadius: 0,
        borderWidth: 2,
        borderColor: 'black'

    },
    textInput5: {
        color: 'black',
        fontSize: 18,
        alignItems: 'center',
        justifyContent: 'center',
        paddingHorizontal: 1,
        paddingVertical: 12,
        width: 298,
        margin: 3,
        borderRadius: 0,
        borderWidth: 2,
        borderColor: 'black'

    },

    pickerStyle: {
        width: 400,
        borderRadius: 0,
        borderWidth: 2,
        margin: 3,
        borderColor: 'black',
        //paddingVertical: 16,
    },

    boxStyle: {
        width: 420,
        borderRadius: 2,
        borderWidth: 3,
        margin: 5,
        borderColor: 'grey',
        //paddingVertical: 16,
    },

    text1: {
        // fontFamily: 'cursive',
        color: 'deepskyblue',
        fontSize: 16,
        // fontWeight: "bold"

    },

    text2: {
        // fontFamily: 'cursive',
        color: 'white',
        fontSize: 10,
        fontWeight: "bold"

    },

});