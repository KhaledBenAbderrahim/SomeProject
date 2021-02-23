import React from 'react';

import ProfileOverview from '../screens/ProfileOverview';

import { createStackNavigator } from '@react-navigation/stack';


const Stack = createStackNavigator();

export default function ProfileRoute({ route, navigation }) {

    return (
        <Stack.Navigator>
            <Stack.Screen
                name="Profile"
                component={ProfileOverview}
                options={{
                    title: 'Profil',
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
                }}
            />
        </Stack.Navigator>
    );
}
