import React from 'react';
import AllReceipe from '../screens/AllReceipe';
import { createStackNavigator } from '@react-navigation/stack';


const Stack = createStackNavigator();

export default function AllReceipeRoute({ route, navigation }) {

    return (
        <Stack.Navigator>
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
                   // headerShown: false,

                }}
            />
        </Stack.Navigator>
    );
}
