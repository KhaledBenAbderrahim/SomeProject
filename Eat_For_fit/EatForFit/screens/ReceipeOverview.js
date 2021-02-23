import React from 'react';
import { StyleSheet, View, Text, Platform, Image,FlatList } from 'react-native';
import { TouchableNativeFeedback } from 'react-native-gesture-handler';
import {DataContext} from '../utils/DataManager';
import { Entypo } from '@expo/vector-icons';
import {  Menu, Divider, Provider } from 'react-native-paper';


const TouchableItem = Platform.OS === 'ios' ? TouchableOpacity : TouchableNativeFeedback;


export default function ReceipeOverview({route,navigation}) {

    const categories =   React.useContext(DataContext);

    
    const [visible, setVisible] = React.useState(false);

    const openMenu = () => setVisible(true);
    const closeMenu = () => setVisible(false);

    const renderItem=( {item} ) => {

        //let img='../assets/CategoriesImg/'+item.name+'.png';
        //console.log(img)

       return( 
            <View 
                    style={{
                        flex: 1,
                        flexDirection: 'column',
                        margin: 2
                    }}>
                    <TouchableItem onPress={() => navigation.navigate('AllReceipe', { item})}>
                    {/*</TouchableItem><TouchableItem onPress={() => navigation.navigate('Salatbar', { cat: item.name})}>*/}
                            <Image
                                resizeMode='contain' 
                                style={styles.itemContainer}
                                source= {item.image}
                            />
                            <Text style={styles.itemName}>{item.name}</Text>
                    </TouchableItem>
            </View>
    );
};
    

    return (
        <View style={styles.container}>

            <View style = {styles.myHeader}>
              <View  style = {styles.headerTitle}>
                    <Text style = {styles.title}> Rezepte</Text>
                </View>

                <View  style = {styles.listMore}>
                        
                    <Menu
                        visible={visible}
                        onDismiss={closeMenu}
                        anchor={<Entypo name="dots-three-vertical" size={24} color="white"  onPress={openMenu} />}>
                                
                        <Menu.Item  onPress={() => navigation.navigate('NeuesRezept')} title='Neues Rezept' />
                        <Divider />
                        {/*<Menu.Item onPress={() => navigation.navigate('Calender')} title='Calender' />*/}
                            
                    </Menu>
                </View>

            </View>
             
             <FlatList
                data={categories.CATEGORIES}
                renderItem={renderItem}
                numColumns= {2}
                
                />
                
        </View>
            
    );
}

const styles = StyleSheet.create({
    container: {
        flex:1,
        
    },
    myHeader: {
        backgroundColor: '#72D3FF',
        flex:0,
        flexDirection: 'row',
        height: 90,
      },
      listMore: { 
        flex:2,
        flexDirection: 'row-reverse', 
        textAlign: "left",
        marginTop: 20,
        padding: 20,
 
      },
      headerTitle: { 
        flex:2,
        flexDirection: 'row', 
        textAlign: "right",
        margin: 15,
        padding: 10,
        
       
      },
      title:{
        fontSize: 30,
        alignContent: 'center',
        color: "#fff",
        fontWeight: "bold",
      },
      itemContainer: {
        
        borderRadius: 10,
        margin: 5,
    
      },
      itemName: {
        fontSize: 28,
        color: '#fff',
        fontWeight: '700',
        backgroundColor: 'rgba(114,211,255,0.5)',
        textAlign: 'center',
        position : "absolute",
        top: 150,
         
      },
    
});
