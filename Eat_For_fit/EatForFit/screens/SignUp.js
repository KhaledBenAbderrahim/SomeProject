import React, { useContext, useState } from 'react';
import { TextInput, SafeAreaView, TouchableOpacity, StyleSheet, View, Text } from 'react-native';
import Registration from './../components/Registration';


export default function SignUp({ route, navigation }) {
    
    return (
        <SafeAreaView style={styles.container}>
          <Registration
            utext={'Registrieren'}
            navigation={navigation}
          />
        </SafeAreaView>
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
        width: 30,
        height: 30
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
    input: {
        margin: 5,
        padding: 6,
        borderRadius: 8,
        marginBottom: 8,
        paddingHorizontal: 10,
        backgroundColor: "#eceff1"
      }
});
