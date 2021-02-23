import React, { useContext, useState } from 'react';
import { Image, TextInput, TouchableOpacity, StyleSheet, View, Text } from 'react-native';

export default function Login2({ route }) {

    return (
        <View style={styles.container}>
            <Image style={styles.image} source={require('../assets/logo.png')} />
            <Text> </Text>
            <Text> </Text>
            <Text> </Text>
            <TextInput
                style={styles.textInput}
                placeholder="Username or E-Mail"                        
            /> 
            <TextInput
                style={styles.textInput}
                placeholder="Password"                        
            /> 

            <Text style={styles.text1} >Forgotten Password?</Text>
            <TouchableOpacity
                style={styles.button}                            
            >
            <Text style={styles.text2}>LOG IN</Text>

            </TouchableOpacity>
       
 
        </View>
    );
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        color: 'white',
        alignItems: 'center',
        justifyContent: 'center',
    },
    image: {
        width: 250,
        height: 250,
        alignItems: 'center',
        justifyContent: 'center',
    },
 
    button: {
        backgroundColor: 'deepskyblue',
        alignItems: 'center',
        justifyContent: 'center',
        paddingHorizontal: 5,
        paddingVertical: 10,
        width: 200,
        margin: 3,
        // borderRadius: 0,
        // borderWidth: 2,
        // borderColor: 'dimgray'

    },

    textInput: {
        //fontFamily: 'cursive',
        color: 'black',
        fontSize: 16,
        //fontWeight: "bold"
        alignItems: 'center',
        justifyContent: 'center',
        paddingHorizontal: 5,
        paddingVertical: 10,
        width: 300,
        margin: 3,
        borderRadius: 0,
        borderWidth: 2,
        borderColor: 'black'

    },
    text1: {
        //fontFamily: 'cursive',
        color: 'deepskyblue',
        fontSize: 16,
        // fontWeight: "bold"

    },

    text2: {
        //fontFamily: 'cursive',
        color: 'white',
        fontSize: 16,
        fontWeight: "bold"

    },
});
