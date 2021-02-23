import React, { useContext, useState } from 'react';
import { SectionList, TextInput, TouchableOpacity, StyleSheet, View, Text, Image } from 'react-native';
import {DataContext} from '../utils/DataManager';



export default function AllReceipe({ route, navigation }) {

    const zutaten =   React.useContext(DataContext);
    let itemCategory = route.params.item;
    
    
    let totalRezepte = zutaten.ZUTATEN.filter(function (rezept){
        return rezept.categories.includes(itemCategory.name)
    })
    
    

    

    return (
        <View style={styles.container}>
          
            <View style = {styles.myHeader}>
                <Text style = {styles.title}> Rezepte von {itemCategory.name}</Text>
            </View> 
            <SectionList
                sections={totalRezepte}
                renderItem={({ item }) =>
                    <View>
                                   
                        {/* <Text style={{ fontSize: 14, fontFamily: 'cursive' }}>    
                            
                            {item.menge}
                            {item.einheit} {item.zutat}
                  

                        </Text> */}
                        </View>
                    }
    
                    renderSectionHeader={({ section }) =>
                    <TouchableOpacity  onPress={() => {
                        console.log("Klicked on " +section.name)
                        console.log("Id:  " +section.receipeID)
                         navigation.navigate('Salatbar2', {receipeID: section.receipeID})
                    
                    }
                        
                        }>
    
                        <View style={styles.alignedInARow}>
                            <Image style={styles.smallImage} source={section.image} /> 
                            <View style={styles.smallText}>
                                <Text style={{ fontSize: 18, fontStyle: 'italic', fontWeight: "bold" }}>
                                    {section.name}
                                </Text>
                                <Text style={{ fontSize: 16, fontStyle: 'italic' }}>
                                    {section.beschreibung}
                                </Text>
                            </View>
                        </View>    
    
                        {/* <View style={styles.sectionHeaderStyle}>
                            <Image style={styles.image} source={section.image} />    
                            <Text style={{ fontSize: 22, fontFamily: 'cursive' }}> 
                            {section.name}
                            </Text>
                            <Text style={{ fontSize: 12, fontFamily: 'cursive' }}>
                            {section.beschreibung}
                            </Text>
                        </View> */}
                        </TouchableOpacity>     
                    }
                />
              
        </View>
    );

}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        color: 'white',  
    },
    myHeader: {
        flex: 0,
        backgroundColor: '#72D3FF',
        height: 90,
        flexDirection: 'row',
        
      },
      title:{
          color: '#fff',
          textAlign: "left",
          fontSize: 30,
          fontWeight: 'bold',
          paddingTop: 20,
          
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

    smallImage: {
        width: 80,
        height: 60,
        paddingHorizontal: 1,
        paddingVertical: 20,
        alignItems: 'center',
        justifyContent: 'center',
    },

    alignedInARow: {
        flex: 1,
        flexDirection: 'row',
        alignItems: 'center',
        justifyContent: 'center',
        paddingVertical: 1,
    },

    smallText: {
        backgroundColor: 'white',
        justifyContent: 'center',
        paddingHorizontal: 5,
        paddingVertical: 10,
        width: 400,
    },

});