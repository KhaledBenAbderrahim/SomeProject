import React from 'react';

import Home from '../screens/Home';
import NeuesRezept from '../screens/NeuesRezept';
import Rezept from '../screens/Rezept';
import Salatbar2 from '../screens/Salatbar2';
import Zubereitung from '../screens/Zubereitung';
import AllReceipe from '../screens/AllReceipe';


import { createStackNavigator } from '@react-navigation/stack';


const Stack = createStackNavigator();

export default function HomeRoute({ route, navigation }) {

    return (
        <Stack.Navigator>
            <Stack.Screen
                name="Home"
                component={Home}
                options={{
                    title: 'EAT FOR FIT',
                    headerStyle: {
                        backgroundColor: '#72D3FF',
                        height: 80,
                        
                    },
                    headerTintColor: '#717171',
                    headerTitleStyle: {
                        fontWeight: 'bold',
                        fontSize: 30,
                        alignContent: 'center',
                    },
                    headerTitleAlign: 'center',
                }}
            />
            <Stack.Screen
                name="NeuesRezept"
                component={NeuesRezept}
                options={{
                    title: 'NeuesRezept',
                    headerStyle: {
                        backgroundColor: 'deepskyblue',
                        height: 80,
                    },
                    headerTintColor: 'white',
                    headerTitleStyle: {
                        //fontFamily: 'Lego',
                        fontSize: 30,
                        alignContent: 'center',
                    },
                    headerTitleAlign: 'center',
                }}
            />
            <Stack.Screen
                name="AllReceipe"
                component={AllReceipe}
                options={{
                    title:'AllReceipe',
                    headerStyle: {
                        backgroundColor: '#0081A1',
                        height: 80,
                    },
                    headerTintColor: 'white',
                    headerTitleStyle: {
                        fontSize: 30,
                        alignContent: 'center',
                    },
                    headerTitleAlign: 'center',
                    headerShown: false,

                }}
            />
            <Stack.Screen
                name="Rezept"
                component={Rezept}
                options={{
                    title: 'Rezept',
                    headerStyle: {
                        backgroundColor: 'deepskyblue',
                        height: 80,
                    },
                    headerTintColor: 'white',
                    headerTitleStyle: {
                        //fontFamily: 'Lego',
                        fontSize: 30,
                        alignContent: 'center',
                    },
                    headerTitleAlign: 'center',
                }}
            />  
            
              <Stack.Screen
                name="Salatbar2"
                component={Salatbar2}
                options={{
                    title: 'Salatbar2',
                    headerStyle: {
                        backgroundColor: 'deepskyblue',
                        height: 80,
                    },
                    headerTintColor: 'white',
                    headerTitleStyle: {
                        //fontFamily: 'Lego',
                        fontSize: 30,
                        alignContent: 'center',
                    },
                    headerTitleAlign: 'center',
                }}
            />  
             <Stack.Screen
                name="Zubereitung"
                component={Zubereitung}
                options={{
                    title: 'Zubereitung',
                    headerStyle: {
                        backgroundColor: 'deepskyblue',
                        height: 80,
                    },
                    headerTintColor: 'white',
                    headerTitleStyle: {
                        //fontFamily: 'Lego',
                        fontSize: 30,
                        alignContent: 'center',
                    },
                    headerTitleAlign: 'center',
                }}
            />  

        </Stack.Navigator>
    );
}
