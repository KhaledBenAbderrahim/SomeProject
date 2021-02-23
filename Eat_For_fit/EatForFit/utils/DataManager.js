import AsyncStorage from '@react-native-async-storage/async-storage';
import React from 'react';

export const DataContext = React.createContext();

export const saveData = async (key, value) => {
    try {
        const jsonValue = JSON.stringify(value)
        await AsyncStorage.setItem(key, jsonValue)
        return value;
    } catch (e) {
        // saving error
    }
}

export const loadData = async (key) => {
    try {
        const jsonValue = await AsyncStorage.getItem(key)
        return jsonValue != null ? JSON.parse(jsonValue) : null;
    } catch (e) {
        // error reading value
    }
}

export const clearData = async (key) => {
    try {
        AsyncStorage.clear(key)
    } catch (e) {
        // error reading value
    }
}