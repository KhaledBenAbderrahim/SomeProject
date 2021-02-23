import React, { useContext, useState } from 'react';
import { StyleSheet, View, Text,Image,TouchableNativeFeedback } from 'react-native';
import { Entypo } from '@expo/vector-icons';
import {  Menu} from 'react-native-paper';
import {DataContext} from '../utils/DataManager';
import {Agenda} from 'react-native-calendars';
import { TouchableOpacity } from 'react-native-gesture-handler';
import {Card, Avatar} from 'react-native-paper';

const TouchableItem = Platform.OS === 'ios' ? TouchableOpacity : TouchableNativeFeedback;


export default function Calender({route,navigation}) {
    
    const receipe = React.useContext(DataContext);
    const [visible, setVisible] = React.useState(false);
    const [items, setItems] = useState({});

    const openMenu = () => setVisible(true);
    const closeMenu = () => setVisible(false);

    const today  = ()=>{
      const date = new Date();
      const strTime = date.toISOString().split('T')[0];
          
      return (
        strTime
      )
    };
    

      const  addDay =(date, days)=>{
        var result = new Date(date);
        result.setDate(result.getDate() + days);
        return result.toISOString().split('T')[0];;
      }
      
    

      const dateToday  = (days)=>{
        const date = new Date();
        const strTime = date.toISOString().split('T')[0];
            
        return (
          addDay(strTime,days)
        )
      };
      
      
      
    const loadItems=(day) => {
        setTimeout(() => {
          for (let i = 0; i < 14; i++) {
            const strTime = dateToday(i);
            console.log(strTime);

            if (!items[strTime]) {
              items[strTime] = [];
              const numItems = 3;
              for (let j = 0; j < numItems; j++) {
                items[strTime].push({
                  name: receipe.ZUTATEN[j].name,
                  receipeID: receipe.ZUTATEN[j].receipeID,
                  beschreibung:receipe.ZUTATEN[j].beschreibung,
                  image: receipe.ZUTATEN[j].imageSmall,
                  gerichtstyp:receipe.ZUTATEN[j].gerichtstyp,
                });
              }
            }
          }
          const newItems = {};
          Object.keys(items).forEach(key => {
            newItems[key] = items[key];
          });
          setItems(newItems);
        }, 15);
      }

      const renderItem = (item) => {
        return (
          <TouchableItem style={{marginRight: 10, marginTop: 17}} 
            onPress={() => navigation.navigate('Salatbar2', {receipeID: item.receipeID})}>
            <Card>
              <Card.Content>
                <View
                  style={{
                    flexDirection: 'row',
                    justifyContent: 'space-between',
                    alignItems: 'center',
                    backgroundColor: '#E6F7FF',
                  }}>
                    <View style={styles.beschreibung}>
                        
                        <Text style={styles.text}>{item.gerichtstyp}</Text>
                    </View>
                  
                        <Image
                                        resizeMode='contain' 
                                        style={styles.itemContainer}
                                        source= {item.image}
                            />
                </View>
              </Card.Content>
            </Card>
          </TouchableItem>
        );
      };




    return (
            <View style={styles.container}>
                <View style = {styles.myHeader}>
                    <View  style = {styles.headerTitle}>
                        <Text style = {styles.title}>Kalender</Text>
                    </View>

                    <View  style = {styles.listMore}>   
                        <Menu
                            visible={visible}
                            onDismiss={closeMenu}
                            anchor={<Entypo name="dots-three-vertical" size={24} color="white"  onPress={openMenu} />}>
                                    
                            <Menu.Item  onPress={() => navigation.navigate('NeuesRezept')} title='Neues Rezept' />
                            
                        </Menu>
                    </View>
                
                </View>


                <View style= {styles.agendeReceipe}>
                    <Agenda
                        
                        items={items}
                        loadItemsForMonth={loadItems}
                        selected={today}
                        renderItem= {renderItem}
                        />

                </View>




            </View>
    );
}

const styles = StyleSheet.create({
    container: {
        flex:1,        
    },
    myHeader: {
        flex: 0,
        backgroundColor: '#72D3FF',
        height: 90,
        flexDirection: 'row',
        
      },
      listMore: { 
        flex: 2,
        flexDirection: 'row-reverse', 
        textAlign: "left",
        marginTop: 20,
        padding: 20,
      },
      headerTitle: { 
        
        flexDirection: 'row', 
        textAlign: "right",
        margin: 15,
        paddingTop: 20,
        
       
      },
      title:{
        fontSize: 30,
        alignContent: 'center',
        color: "#fff",
        fontWeight: "bold",
      },
      agendeReceipe:{
          flex: 1,
      },
      beschreibung:{
        
        
      },
      text:{
        fontSize: 26,
      },
});
