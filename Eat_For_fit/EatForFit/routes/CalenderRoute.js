import React from 'react';

import Calender from '../screens/Calender';

import { createStackNavigator } from '@react-navigation/stack';


const Stack = createStackNavigator();

export default function CalenderRoute({ route, navigation }) {

    return (
        <Stack.Navigator>
            <Stack.Screen
                name="Calender"
                component={Calender}
                options={{
                    title: 'Kalender',
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
        </Stack.Navigator>
    );
}
