import React, { useContext, useState } from 'react';
import { StyleSheet, View, Text, TextInput, TouchableOpacity } from 'react-native';
import { MaterialIcons } from '@expo/vector-icons'; 

export default function ProfileOverview({route}) {

    return (
            <View style={styles.container}>
                <Text> </Text>
                
                <View style={styles.alignedInARow}>

                    <TouchableOpacity onPress={() => { alert( "Person outline" ) }}>
                        <MaterialIcons name="person-outline" size={24} color="black" />
                    </TouchableOpacity>

                    <TouchableOpacity onPress={() => { alert( "Library books" ) }}>
                        <MaterialIcons name="library-books" size={24} color="black" />
                    </TouchableOpacity>

                </View>
                <Text> </Text>    

            <TextInput
                style={styles.textInput}
                placeholder="Name"
            />
            <TextInput
                style={styles.textInput}
                placeholder="E-Mail"
            />
            <TextInput
                style={styles.textInput}
                placeholder="Password"
            />
            <TextInput
                style={styles.textInput}
                placeholder="Alter"
            /> 
            </View>
    );
}

const styles = StyleSheet.create({
    container: {
        // flex:1,  
        color: 'white',
        alignItems: 'center',
        justifyContent: 'center',    
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
    alignedInARow: {
        flex: 1,
        flexDirection: 'row',
        alignItems: 'center',
        justifyContent: 'center',
        paddingVertical: 1,
        height: 100
    },
});
