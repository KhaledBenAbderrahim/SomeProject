import React, { useContext, useState } from 'react';
import { SectionList, TextInput, TouchableOpacity, StyleSheet, View, Text, Image } from 'react-native';

import { zutaten } from '../utils/tempDataList';

export default function Salatbar2({ route, navigation }) {

    const { receipeID } = route.params;

    console.log("*** Search start ***")

    console.log("We got: " +receipeID)

    var zutaten2 = []

    console.log('Number of elements: ' +zutaten.length)

    var indexID = 0

    for(var i = 0; i < zutaten.length; i++){
        console.log("Recipe number: " +i)
        console.log(zutaten[i].receipeID)
        if(receipeID == zutaten[i].receipeID){
            console.log("Gefunden mit index " +i)
            indexID = i
            zutaten2.push(zutaten[i])
            } else {console.log("Nicht gefunden")}
        }
     
        console.log("*** Search end ***")
   

    return (
        <View style={styles.container}>
          
                      
            <SectionList
                sections={zutaten2}
                renderItem={({ item }) =>
                    <View>
                                   
                        <Text style={{ fontSize: 18 }}>    
                            
                            {item.menge}
                            {item.einheit} {item.zutat}
                  

                        </Text>
                    </View>
                }

                renderSectionHeader={({ section }) =>
                    <View style={styles.sectionHeaderStyle}>
                        <Image style={styles.image} source={section.image} />    
                        <Text style={{ fontSize: 22,  fontWeight: "bold"}}> 
                        {section.name}
                        </Text>
                        <Text style={{ fontSize: 12 }}>
                         {section.time}, {section.gerichtstyp}
                        </Text>
                        <Text style={{ fontSize: 18, fontWeight: "bold" }}>
                Zutaten 
             </Text>
            <Text>---------------------------------------------------------------------------------------------------------------------</Text>  
                    </View>
                }
            />

            <View>
                <TouchableOpacity style = {styles.button}
                onPress={() => { navigation.navigate('Zubereitung', {indexID: indexID}) }}>
                <Text style={styles.text2}>Zubereitung</Text>
                </TouchableOpacity>
            </View>

        </View>
    );

}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        color: 'white',
        alignItems: 'center',
        justifyContent: 'center',
    },

    sectionHeaderStyle: {
        backgroundColor: 'white',
        // alignItems: 'left',
        justifyContent: 'center',
        paddingHorizontal: 5,
        paddingVertical: 10,
        width: 480,
    },
    image: {
        width: 480,
        height: 280,
        paddingHorizontal: 5,
        paddingVertical: 60,
        alignItems: 'center',
        justifyContent: 'center',
    },
    button: {
        backgroundColor: 'salmon',
        alignItems: 'center',
        justifyContent: 'center',
        paddingHorizontal: 10,
        paddingVertical: 15,
        width: 150,
        margin: 3,
        borderRadius: 12,


    }, 
    text2: {
        //fontFamily: 'cursive',
        color: 'white',
        fontSize: 20,
        fontWeight: "bold"

    },

});