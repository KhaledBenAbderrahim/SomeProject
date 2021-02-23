import React, { useEffect, useState } from 'react';
import { SafeAreaView, StyleSheet, View, Text } from 'react-native';
import { DataContext, loadData, saveData, clearData } from './utils/DataManager';

import { NavigationContainer } from '@react-navigation/native';
import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';
import { DefaultTheme, Provider as PaperProvider } from 'react-native-paper';

import HomeRoute from './routes/HomeRoute';
import ReceipeRoute from './routes/ReceipeRoute';
import CalenderRoute from './routes/CalenderRoute';
import ProfileRoute from './routes/ProfileRoute';
import SettingsRoute from './routes/SettingsRoute';

import { FontAwesome } from '@expo/vector-icons';

import { user, shoppinglist, categories,zutaten } from './utils/tempDataList';
import {User} from './classes/user';

import { AppLoading } from 'expo';
import { useFonts } from 'expo-font';
import { insertUser, getUserByKey, saveUser, getAllUsers } from './utils/UserController';
import { getProfileByUser } from './utils/ProfileController';

const Tab = createBottomTabNavigator();

export default function App() {

  const [USER, setUserData] = useState(null);
  const [USERPROFILE, setUserProfileData] = useState(null);

  let [fontsLoaded] = useFonts({
    //'Test': require('./assets/fonts/test.ttf'),
  });

  //Async Load / Datenbankanbindung3
  useEffect(() => {
    const fetch = async () => {
      //await clearData("userKey");

      /******
       userKey als lokale Varibale genutzt.     
       Muss an eigenes System angepasst werden
      ******/

      //let userKey = await loadData("userKey");
      let userKey = "06d5b01d-ef98-46d7-97a7-7809754ed5ef";
      //let userKey = null;


      console.log(userKey);
      let dbUser = undefined;
      let profile = undefined;
      if(userKey == null){
        dbUser = await insertUser();
        await saveData("userKey", dbUser.userKey);
        profile = await getProfileByUser(dbUser)
      }
      else{
        dbUser = await getUserByKey(userKey);
        profile = await getProfileByUser(dbUser)
        //console.log(dbUser);
      }
      //console.log(dbUser)

      setUserData(dbUser)      
      setUserProfileData(profile)
    }
    fetch()
  }, [])

  /*useEffect(() => {
    let user = new User(0, 'awödlkawid', 'Bernd', 'Hubinger', new Date(9876543210135));
    const fetch = async () => {
      let users = await getAllUsers()
      console.log(users)
    }
    fetch()
  }, [])*/

  /*const [USER, setUserData] = useState(
    () => {
      const fetch = async () => {
        let load = await loadData('user');
        load != null ? setUserData(load) : setUserData(user)
      };
      fetch()
    }
  );*/
  
  //Predefined List

  const [CATEGORIES, setCategoryData] = useState(categories);
  const [ZUTATEN, setZutatenData] = useState(zutaten);


  if (!fontsLoaded) {
    return <AppLoading />;
  }

  return (
    <DataContext.Provider
      value={
        {
          //Globale Konstanten
          CATEGORIES: CATEGORIES,
          ZUTATEN: ZUTATEN,
          USER: USER,
          USERPROFILE: USERPROFILE,

          //Konstanten Setter-Methoden
          setUserData: setUserData,
          setUserProfileData: setUserProfileData,
        }
      }>
      <PaperProvider theme={{ ...DefaultTheme, 
        colors: { ...DefaultTheme.colors, primary: '#72D3FF' },
      }}>
        <NavigationContainer>
          <Tab.Navigator
            //drawerContentOptions={{ inactiveTintColor: 'white', labelStyle: { fontSize: 24 }, }}
            tabBarOptions={{
              activeTintColor: '#72D3FF',
              showLabel: false,
            }}
          >
            <Tab.Screen
              name="Home"
              component={HomeRoute}
              options={{
                tabBarLabel: 'Home',
                tabBarIcon: ({ color, size }) => (
                  <FontAwesome name="home" color={color} size={size} />
                ),
              }}
            />
            <Tab.Screen
              name="Receipes"
              component={ReceipeRoute}
              options={{
                tabBarLabel: 'Rezepte',
                tabBarIcon: ({ color, size }) => (
                  <FontAwesome name="book" color={color} size={size} />
                ),
              }}
            />
            <Tab.Screen
              name="Calender"
              component={CalenderRoute}
              options={{
                tabBarLabel: 'Kalender',
                tabBarIcon: ({ color, size }) => (
                  <FontAwesome name="calendar" color={color} size={size} />
                ),
              }}
            />
            <Tab.Screen
              name="Profile"
              component={ProfileRoute}
              options={{
                tabBarLabel: 'Profil',
                tabBarIcon: ({ color, size }) => (
                  <FontAwesome name="user-circle" color={color} size={size} />
                ),
              }}
            />
            <Tab.Screen
              name="Settings"
              component={SettingsRoute}
              options={{
                tabBarLabel: 'Einstellungen',
                tabBarIcon: ({ color, size }) => (
                  <FontAwesome name="cog" color={color} size={size} />
                ),
              }}
            />
          </Tab.Navigator>
        </NavigationContainer>
      </PaperProvider>
    </DataContext.Provider>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#323232',
    alignItems: 'center',
    justifyContent: 'center',
  },
});
