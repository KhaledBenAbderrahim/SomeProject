import React from 'react';
import ReceipeOverview from '../screens/ReceipeOverview';
import { createStackNavigator } from '@react-navigation/stack';



const Stack = createStackNavigator();

export default function ReceipeRoute({ route, navigation }) {

    const [visible, setVisible] = React.useState(false);

    const openMenu = () => setVisible(true);
    const closeMenu = () => setVisible(false);
    console.log(openMenu)

    return (
        <Stack.Navigator>
            <Stack.Screen
                name="Receipes"
                component={ReceipeOverview}
                options={{
                    title: 'Rezepte',
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
                    headerTitleAlign: 'right',
                    headerShown: false,
                }}
            />
        </Stack.Navigator>
    );
}


