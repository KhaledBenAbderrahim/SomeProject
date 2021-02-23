import React from 'react';
import { User } from '../classes/user';
import { getHTTP } from './db_connect';

export const insertUser = async () => {
    try {
        let response = await fetch(
           getHTTP() + '/user/insert', {
            method: 'GET',
        });
        let json = await response.json();
        //console.log(json);
        return json;
    } catch (error) {
        console.error(error);
    }
};

export const getAllUsers = async () => {
    try {
        let response = await fetch(
            getHTTP() + '/users/get', {
            method: 'GET',
        });
        let json = await response.json();
        //console.log(json);
        return json;
    } catch (error) {
        console.error(error);
    }
};

export const getUserByKey = async (userKey) => {
    try {
        let response = await fetch(
            getHTTP() + '/user/get', {
            method: 'POST',
            headers: {
                Accept: 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(
                {
                    userKey: userKey,
                }
            )
        });
        let json = await response.json();
        //console.log(json);
        return json;
    } catch (error) {
        console.error(error);
    }
};

export const saveUser = async user => {
    try {
        let response = await fetch(
            getHTTP() + '/user/save', {
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

export const deleteUser = async user => {
    try {
        fetch(
            getHTTP() + '/user/delete', {
            method: 'DELETE',
            headers: {
                Accept: 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(user)
        });
    } catch (error) {
        console.error(error);
    }
};