import React, {useRef, useState} from 'react';   
import { TouchableOpacity, StyleSheet, View, Text, TouchableNativeFeedback, Image, Dimensions, ScrollView, FlatList } from 'react-native';
import {DataContext} from '../utils/DataManager';

import { Entypo } from '@expo/vector-icons';
import { Fontisto } from '@expo/vector-icons';
import { FontAwesome5 } from '@expo/vector-icons';
import Carousel from 'react-native-anchor-carousel';
const { width } = Dimensions.get('window');
//npm install react-native-anchor-carousel

const TouchableItem = Platform.OS === 'ios' ? TouchableOpacity : TouchableNativeFeedback;

export default function Home({route, navigation}) {

  const [counter, setCounter] = useState([]);
  const [newArray, setArray] = useState([]);

  React.useEffect(() => {

    console.log("We are in the function Home");

    if (route.params?.gerichtstyp) {

      var newArray2 = newArray.slice();
      
      newArray2.push(
        { receipeID: counter,
          name: route.params?.name,
          time: route.params?.time,
          image: route.params?.image,
          imageSmall: route.params?.imageSmall,
          beschreibung: route.params?.beschreibung,
          gerichtstyp: route.params?.gerichtstyp,
          categories: route.params?.categories,
          data: route.params?.data,
          schritt: route.params?.schritt } );
        
      setArray(newArray2);
      setCounter(counter+1);

      console.log("Pushed!")

    } else {
        console.log("We haven't recieved anything!")}

    console.log("We are still in the function Home");

  }, [route.params?.gerichtstyp]);
  
    const receipe = React.useContext(DataContext);
    const carouselRef = useRef(null);

  const renderItem = ({item}) => {

      return (
          <TouchableOpacity
              onPress={() => navigation.navigate('Salatbar2', {receipeID: item.receipeID})}>
               <Image
                      
                      style={styles.itemContainer}
                      source= {item.imageSmall}
                />
                
          </TouchableOpacity>)
  };
    
    return (

      <View style={styles.container}>

      <ScrollView style={styles.scrollView}>
          <View style={styles.carouselContainer}>
            <Text style= {styles.titleCarousel}> Neu und Angesagt</Text>
              <Carousel style={styles.carousel}
                      data={receipe.ZUTATEN}
                      renderItem={renderItem}
                      itemWidth={190} 
                      containerWidth={width}
                      separatorWidth={20}
                      ref={carouselRef}
                  //pagingEnable={false}
                          />

          </View>

            <View style={styles.element}>

                <View style={{flexDirection:'row' }}>
                    <TouchableItem onPress={() => navigation.navigate('Profile')}>
                        <View style={[styles.boxi, styles.box1]}>
                          <Fontisto name="favorite" size={30} color="#72D3FF" />
                          <Text style={styles.text}> Favoriten</Text>
                        </View>
                    </TouchableItem>

                    <TouchableItem onPress={() => navigation.navigate('NeuesRezept')}>
                        <View style={[styles.boxi, styles.box2]}>
                        <Entypo name="new-message" size={30} color="#77D353" />                     
                        <Text style={styles.text}>Neues Rezept</Text>
                        </View>
                    </TouchableItem>
                </View>

                <View style={{flexDirection:'row'}}>

                    <TouchableItem onPress={() => navigation.navigate('NeuesRezept')}>
                          <View style={[styles.boxi, styles.box1]}>
                              <FontAwesome5 name="nutritionix" size={30} color="#9b4210" /> 
                              <Text style={styles.text}> Nährstoffe</Text>
                          </View>
                    </TouchableItem>
                    <TouchableItem onPress={() => navigation.navigate('Profile')}>
                          <View style={[styles.boxi, styles.box2]}>
                              <Entypo name="help" size={30} color="#FF9052" />
                              <Text style={styles.text}> Hilfe</Text>
                          </View>

                    </TouchableItem>
                </View>

          </View>  
          </ScrollView>

          <Text>Recipes:</Text>
          <FlatList
                data={newArray}
                renderItem={({ item }) =>
                    <View>
                    <Text>Recipe: {item.name} </Text>
                    <Text>{item.beschreibung} </Text>
                    </View>
                }
          />

        </View>
    

    );
}

const styles = StyleSheet.create({
    container: {
        flex:1,    
        flexDirection: 'column',
        backgroundColor: '#E6F7FF',
        
    },
    carouselContainer: {
     
      height:330,
      
    },
    carousel: {
      flex:1,
    },
    titleCarousel:{
      color:'#343F4B',
      borderBottomWidth: 2,
      borderBottomColor: '#343F4B',
      fontSize: 28,
      textAlign: 'center',
      fontWeight:'bold',
      paddingTop: 10,
      
    },
    itemName:{
      fontSize: 20,
    },
    element: {
      flex:1,
      paddingTop: 10,
      //backgroundColor: 'red', 
      alignContent: 'space-between', 
      flexDirection:'column',

    }
   
    ,
    boxi:{
      flex:1,
      borderWidth: 2,
      backgroundColor: '#fff',
      borderRadius: 10,
      borderColor: '#4c8544',
      margin: 5,
      height: 140,
      alignItems:"center",
      shadowColor: "#000",
      shadowOffset: {
        width: 0,
        height: 12,
      },
      shadowOpacity: 0.58,
      shadowRadius: 16.00,

      elevation: 24,
       
    },
    //align-self: flex-start|flex-end|center|baseline|stretch;
    box1:{
     alignSelf: 'flex-start',
      justifyContent: 'center',
    },
    box2:{
      alignSelf: 'flex-end',
      justifyContent: 'center',
    },
    text:{
      fontSize: 28,
    },
    

});
