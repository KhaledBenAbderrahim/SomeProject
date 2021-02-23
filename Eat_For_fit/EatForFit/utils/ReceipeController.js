import React from 'react';
import { Receipe } from '../classes/receipe';
import { getHTTP } from './db_connect';

export const getReceipes = async (receipes) => {
    try {
        let response = await fetch(
            getHTTP() + '/receipes/get', {
            method: 'POST',
            headers: {
                Accept: 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(
                {
                    receipes: receipes,
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

export const getReceipebyId = async (receipeId) => {
    try {
        let response = await fetch(
            getHTTP() + '/receipe/get', {
            method: 'POST',
            headers: {
                Accept: 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(
                {
                    receipeID: receipeId,
                }
            )
        });
        let json = await response.json();
        //console.log(json);
        return json;
    } catch (error) {
        console.error(error);
    }

    export const saveReceipe = async receipe => {
        try {
            let response = await fetch(
                getHTTP() + '/receipe/save', {
                method: 'POST',
                headers: {
                    Accept: 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(receipe)
            });
            let json = await response.json();
            //console.log(json);
            return json;
        } catch (error) {
            console.error(error);
        }
    };
};

export const deleteReceipe = async receipe => {
    try {
        fetch(
            getHTTP() + '/receipe/delete', {
            method: 'DELETE',
            headers: {
                Accept: 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(receipe)
        });
    } catch (error) {
        console.error(error);
    }
};

