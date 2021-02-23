import React from 'react';

import Settings from '../screens/Settings';
import Login from '../screens/Login';
import Login2 from '../screens/Login2';
import SignUp from '../screens/SignUp';

import { createStackNavigator } from '@react-navigation/stack';

const Stack = createStackNavigator();

export default function SettingsRoute({ route, navigation }) {

    return (
        <Stack.Navigator>
            <Stack.Screen
                name="Settings"
                component={Settings}
                options={{
                    title: 'Einstellungen',
                    headerStyle: {
                        backgroundColor: '#0081A1',
                        height: 80,
                    },
                    headerTintColor: 'white',
                    headerTitleStyle: {
                        //fontFamily: 'Lego',
                        fontSize: 30,
                        alignContent: 'center',
                    },
                    headerTitleAlign: 'center',
                    headerShown: false,
                }}
            />
            <Stack.Screen
                name="Login"
                component={Login}
                options={{
                    title: 'Login',
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
                name="Login2"
                component={Login2}
                options={{
                    title: 'Login2',
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
                name="SignUp"
                component={SignUp}
                options={{
                    title: 'SignUp',
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
