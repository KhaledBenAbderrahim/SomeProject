import React, { useContext, useState } from 'react';
import { DataContext } from '../utils/DataManager';
import { TouchableOpacity, StyleSheet, View, SectionList } from 'react-native';
import SettingsSwitchItem from '../components/SwitchItem';
import SectionHeader from '../components/SectionHeader';

import { Appbar, Button, Provider, Surface, Divider, Text, Menu } from 'react-native-paper';
import { Entypo } from '@expo/vector-icons';



export default function Settings({route, navigation}) {
    
    const Context = useContext(DataContext);

    const [visible, setVisible] = React.useState(false);
    const openMenu = () => setVisible(true);
    const closeMenu = () => setVisible(false);

    const [diabetisSwitch, setDiabetisSwitch] = React.useState(() => Context.USERPROFILE.diabetis === 1 ? true : false );
    const [glutenSwitch, setGlutenSwitch] = React.useState(() => Context.USERPROFILE.gluten === 1 ? true : false);
    const [lactoseSwitch, setLactoseSwitch] = React.useState(() => Context.USERPROFILE.lactose === 1 ? true : false);

    const [fitnessSwitch, setFitnessSwitch] = React.useState(() => Context.USERPROFILE.fitness === 1 ? true : false);
    const [learncookSwitch, setLearncookSwitch] = React.useState(() => Context.USERPROFILE.learncook === 1 ? true : false);
    const [loseweightSwitch, setLoseweightSwitch] = React.useState(() => Context.USERPROFILE.loseweigth === 1 ? true : false);

    const onToggleDiabetisSwitch = () => setDiabetisSwitch(!diabetisSwitch);
    const onToggleGlutenSwitch = () => setGlutenSwitch(!glutenSwitch);
    const onToggleLactoseSwitch = () => setLactoseSwitch(!lactoseSwitch);

    const onToggleFitnessSwitch = () => setFitnessSwitch(!fitnessSwitch);
    const onToggleLearncookSwitch = () => setLearncookSwitch(!learncookSwitch);
    const onToggleLoseweightSwitch = () => setLoseweightSwitch(!loseweightSwitch);

    const intolerances = [
        {
            "key" : "i1",
            "text" : "Diabetis",
            "switchValue": diabetisSwitch,
            "switchToggle": onToggleDiabetisSwitch,
        },
        {
            "key": "i2",
            "text": "Gluten",
            "switchValue": glutenSwitch,
            "switchToggle": onToggleGlutenSwitch,
        },
        {
            "key": "i3",
            "text": "Lactose",
            "switchValue": lactoseSwitch,
            "switchToggle": onToggleLactoseSwitch,
        }
    ]

    const goals = [
        {
            "key": "g1",
            "text": "Fit werden",
            "switchValue": fitnessSwitch,
            "switchToggle": onToggleFitnessSwitch,
        },
        {
            "key": "g2",
            "text": "Kochen lernen",
            "switchValue": learncookSwitch,
            "switchToggle": onToggleLearncookSwitch,
        },
        {
            "key": "g3",
            "text": "Gewicht verlieren",
            "switchValue": loseweightSwitch,
            "switchToggle": onToggleLoseweightSwitch,
        }
    ]

    const profileList = [
        {
            key: "1",
            title: "Unverträglichkeiten",
            data: intolerances,
        },
        {
            key: "2",
            title: "Ziele",
            data: goals,
        },
    ];

    //console.log(profileList);

    const _handleSearch = () => console.log('Searching');

    const _handleMore = () => console.log('Shown more');

    return (
            <View style={styles.container}>
            
                <Appbar.Header>
                    <Appbar.Content title="Einstellungen" />
                    <Appbar.Action icon="magnify" onPress={_handleSearch} />
                        <Menu
                            style={{elevation:10}}
                            visible={visible}
                            onDismiss={closeMenu}
                            anchor={<Appbar.Action icon="dots-vertical" onPress={openMenu} />}>

                            <Menu.Item onPress={() => navigation.navigate('NeuesRezept')} title='Neues Rezept' />
                            <Divider />
                            <Menu.Item onPress={() => navigation.navigate('Calender')} title='Calender' />

                        </Menu>
                    
                </Appbar.Header>
            
            <SectionHeader label={"Benutzerdaten"} />

            <Surface style={styles.surface}>
                <View style={styles.item_left}>
                    <Text>Benutzername:</Text>
                </View>
                <View style={styles.item_right}>
                    <Text>{Context.USER.nickname}</Text>
                </View>
            </Surface>

            <Surface style={styles.surface}>
                <View style={styles.item_left}>
                    <Text>Vor- und Nachname:</Text>
                </View>
                <View style={styles.item_right}>
                    <Text>{Context.USER.userProfile.firstname} {Context.USER.userProfile.lastname}</Text>
                </View>
            </Surface>

            <Surface style={styles.surface}>
                <View style={styles.item_left}>
                    <Text>Geburtstag:</Text>
                </View>
                <View style={styles.item_right}>
                    <Text>{Context.USER.userProfile.birthdate}</Text>
                </View>
            </Surface>

            <Surface style={styles.surface}>
                <View style={styles.item_left}>
                    <Text>Geschlecht:</Text>
                </View>
                <View style={styles.item_right}>
                    <Text>{Context.USER.userProfile.sex}</Text>
                </View>
            </Surface>

            <SectionList
                sections={profileList}
                renderItem={
                    ({ item }) => 
                        <SettingsSwitchItem 
                            item={item} 
                            context={Context} 
                        />
                }
                renderSectionHeader={({ section }) => 
                    <SectionHeader label={section.title} />
                }
            />
            
            <Button 
                mode="contained"
                onPress={() => {navigation.navigate('Login')}}>
                <Text>LogIn</Text>
            </Button>
            </View>
    );
}

const styles = StyleSheet.create({
    container: {
        flex:1, 
    },
    surface: {
        flex: 1,
        flexDirection: 'row',
        marginHorizontal: 16,
        padding: 8,
        alignItems: 'center',
        justifyContent: 'center',
        elevation: 3,
    },
    item_left: {
        flex: 1,
        flexDirection: 'column',
    },
    item_right: {
        flexDirection: 'row-reverse',
        alignItems: 'center',
        marginLeft: 5,
    },
});


