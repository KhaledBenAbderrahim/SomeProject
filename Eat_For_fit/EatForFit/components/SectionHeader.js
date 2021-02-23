import React from 'react';
import { View, StyleSheet } from 'react-native';
import { Surface, Text } from 'react-native-paper';

export default function SectionHeader({label}) {    

    return(
        <Surface style={styles.section_container}>
            <View style={styles.section_left_container}>
                <Text style={styles.section_head_text}>
                    {label}
                </Text>
            </View>
            <View style={styles.section_right_container}>
                <Text style={styles.section_head_text, { fontSize: 20 }}>

                </Text>
            </View>
        </Surface>
    )    
}

const styles = StyleSheet.create({
    section_container: {
        flex: 1,
        flexDirection: 'row',
        backgroundColor: 'white',
        padding: 5,
        marginVertical: 8,
        marginHorizontal: 16,
        shadowColor: "#000",
        shadowOffset: {
            width: 0,
            height: 2,
        },
        shadowOpacity: 0.23,
        shadowRadius: 2.62,
        elevation: 3,
    },
    section_left_container:{
        flex: 1,
        paddingLeft: 10,
    },
    section_right_container: {
        flexDirection: 'row-reverse',        
    },
    section_head_text: {
        color: 'grey',
        fontSize: 12,
    },
});