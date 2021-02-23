import React, { useContext, useState } from 'react';
import { StyleSheet, View } from 'react-native';
import { Switch, Surface, Text } from 'react-native-paper';

import { saveUser } from '../utils/UserController';
import { saveProfile } from '../utils/ProfileController';

export default function SettingsSwitchItem(props){
    //console.log(props.item)
    const{text, switchValue, switchToggle} = props.item;
    const {context} = props;
    //console.log(props)

    const updateProfile = async () => {
        switchToggle()
        //console.log(switchValue)
        let userToSave = Object.assign({}, context.USER)
        switch (props.item.key) {
            case "i1":
                userToSave.userProfile.diabetis = 
                    !switchValue === false ? 
                    userToSave.userProfile.diabetis = 0 : 
                    userToSave.userProfile.diabetis = 1;
                break;
            case "i2":
                userToSave.userProfile.gluten =
                    !switchValue === false ?
                    userToSave.userProfile.gluten = 0 :
                    userToSave.userProfile.gluten = 1;
                break;
            case "i3":
                userToSave.userProfile.lactose =
                    !switchValue === false ?
                    userToSave.userProfile.lactose = 0 :
                    userToSave.userProfile.lactose = 1;
                break;
            case "g1":
                userToSave.userProfile.fitness =
                    !switchValue === false ?
                    userToSave.userProfile.fitness = 0 :
                    userToSave.userProfile.fitness = 1;
                break;
            case "g2":
                userToSave.userProfile.learncook =
                    !switchValue === false ?
                    userToSave.userProfile.learncook = 0 :
                    userToSave.userProfile.learncook = 1;
                break;
            case "g3":
                userToSave.userProfile.loseweight =
                    !switchValue === false ?
                    userToSave.userProfile.loseweight = 0 :
                    userToSave.userProfile.loseweight = 1;
                break;
        
            default:
                break;
        }
        
        let savedProfile = await saveUser(userToSave)
        
        //console.log(savedProfile)
        context.setUserProfileData(savedProfile)
        
    }

    return (
        <Surface style={styles.surface}>
            <View style={styles.item_left}>
                <Text>
                    {text}
                    </Text>
            </View>
            <View style={styles.item_right}>
                <Switch value={switchValue} onValueChange={updateProfile}/>
            </View>

        </Surface>
    )

}

const styles = StyleSheet.create({
    surface: {
        flex: 1,
        flexDirection: 'row',
        marginHorizontal: 16,
        padding: 8,
        alignItems: 'center',
        justifyContent: 'center',
        elevation: 4,
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