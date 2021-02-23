import React from 'react';
import { Profile } from '../classes/profile';
import { getHTTP } from './db_connect';

export const getProfileByUser = async (user) => {
    try {
        let response = await fetch(
            getHTTP() + '/profile/get', {
            method: 'POST',
            headers: {
                Accept: 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(user)
        });
        let json = await response.json();
        //console.log(json);
        return json;
    } catch (error) {
        console.error(error);
    }
};

export const saveProfile = async profile => {
    try {
        let response = await fetch(
            getHTTP() + '/profile/save', {
            method: 'POST',
            headers: {
                Accept: 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(profile)
        });
        let json = await response.json();
        //console.log(json);
        return json;
    } catch (error) {
        console.error(error);
    }
};

export const deleteProfile = async profile => {
    try {
        fetch(
            getHTTP() + '/profile/delete', {
            method: 'DELETE',
            headers: {
                Accept: 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(profile)
        });
    } catch (error) {
        console.error(error);
    }
};