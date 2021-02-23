import React, { useContext, useState } from 'react';
import { Image, TouchableOpacity, StyleSheet, View, Text } from 'react-native';

export default function Login({ route, navigation }) {

    return (
        <View style={styles.container}>
            <Image style={styles.image} source={require('../assets/logo.png')} />
            <TouchableOpacity
                style={styles.button1}
            >
                <Text style={styles.text1}>LOGIN WITH FACEBOOK</Text>
            </TouchableOpacity>
            <TouchableOpacity
                style={styles.button1}
            >
                <Text style={styles.text1}>LOGIN WITH GOOGLE</Text>
            </TouchableOpacity>
            <TouchableOpacity
                style={styles.button1}
                onPress={() => { navigation.navigate('Login2') }}
            >
                <Text style={styles.text1}>NORMAL LOGIN</Text>
            </TouchableOpacity>
            <Text>------------------  OR  -------------------</Text>
            <TouchableOpacity
                style={styles.button2}
                onPress={() => { navigation.navigate('SignUp') }}
            >
                <Text style={styles.text2}>SIGN UP</Text>
            </TouchableOpacity>
        </View>
    );
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        color: 'deepskyblue',
        alignItems: 'center',
        justifyContent: 'center',
    },
    image: {
        width: 280,
        height: 280,
        paddingHorizontal: 5,
        paddingVertical: 60,
        alignItems: 'center',
        justifyContent: 'center',
    },
    button1: {
        backgroundColor: 'white',
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
    button2: {
        backgroundColor: 'dimgray',
        alignItems: 'center',
        justifyContent: 'center',
        paddingHorizontal: 5,
        paddingVertical: 10,
        width: 300,
        margin: 3,
        // borderRadius: 0,
        // borderWidth: 2,
        // borderColor: 'dimgray'

    },

    text1: {
        //fontFamily: 'cursive',
        color: 'black',
        fontSize: 16,
        fontWeight: "bold"

    },

    text2: {
        //fontFamily: 'cursive',
        color: 'white',
        fontSize: 16,
        fontWeight: "bold"

    },
});
