import React, { useContext, useState } from 'react';
import { SectionList, TextInput, TouchableOpacity, StyleSheet, View, Text, Image } from 'react-native';

import { zutaten } from '../utils/tempDataList';

export default function Zubereitung({ route }) {
    
    const { indexID } = route.params;
            
    return (
        <View style={styles.container}>
                               
            <SectionList
                sections={zutaten[indexID].schritt}
                renderItem={({ item }) =>
                    <View>
                        <Text style={{fontSize: 14, fontWeight: "bold"}}>
                            Zutaten: </Text>                                   
                        <Text style={{ fontSize: 14 }}>{item.zutat} {item.menge} {item.einheit} 
                        </Text>
                    </View>
                }

                renderSectionHeader={({ section }) =>
                    <View style={styles.sectionHeaderStyle}>
                        {/* <Image style={styles.image} source={section.image} />     */}
                        <Text style={{ fontSize: 22 }}> 
                        Schritt {section.id}: {section.text}
                        </Text>
                      
                    </View>
                }
            />

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

});