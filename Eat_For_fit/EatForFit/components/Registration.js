import React, { useContext, Component } from 'react';
import { Button, TextInput, ScrollView, StyleSheet, TouchableOpacity, Text, View } from 'react-native';
import {saveUser} from '../utils/UserController';
import {DataContext} from '../utils/DataManager';
import DateTimePicker from '@react-native-community/datetimepicker';
import { Picker } from "@react-native-community/picker";



export default function Registration(props)  {
    //Table "users"
    const [nickName, setNickName] = React.useState('');
    const [email, setEmail] = React.useState('');
    const [password, setPassword] = React.useState('');
    const [passwordConform, setPasswordConform] = React.useState('');
    //Table "profiles"
    const [birthdate, setBirthdate] = React.useState(new Date(Date.now()));
    const [firstname, setFirstname] = React.useState('');
    const [lastname, setLastname] = React.useState('');
    const [sex, setSex] = React.useState('');

    const [date, setDate] = React.useState(new Date(Date.now()));
    const [mode, setMode] = React.useState('date');
    const [show, setShow] = React.useState(false);
    const savedUser = useContext(DataContext).USER;
    const setUserData = useContext(DataContext).setUserData;
    let navigation = props.navigation;

    const onChange = (event, selectedDate) => {
      if (event['type'] != 'dismissed') {
        setShow(Platform.OS === 'ios');
        const currentDate = selectedDate || date;
        setDate(currentDate);
        setBirthdate(currentDate);
      }
      else {
        setShow(false);
      }      
    };
  
    const showMode = (currentMode) => {
      setShow(true);
      setMode(currentMode);
    };
  
    const showDatepicker = () => {
      showMode('date');
    };
  
    const saveUserDatas = (nickName, firstname, lastname, email, sex, password, UserStatus ) => {

      let userItem = Object.assign({}, savedUser);
      
      userItem.email = email;
      userItem.nickName = nickName;
      userItem.password = password;
      userItem.userProfile.birthdate = birthdate;
      userItem.userProfile.firstname = firstname;
      userItem.userProfile.lastname = lastname;
      userItem.userProfile.sex = sex;
      userItem.UserStatus = UserStatus.BASIC;
      
      setUserData(userItem);
      saveUser(userItem);
      navigation.navigate('Settings');
}

    return (
        <View style={styles.container}>
            <TextInput
                style={styles.textInput}
                onChangeText={text => setNickName(text)}
                value={nickName}
                placeholder="*Nickname"
            />
            <TextInput
                style={styles.textInput}
                onChangeText={text => setFirstname(text)}
                value={firstname}
                placeholder="*Vorname"
            />
            <TextInput
                style={styles.textInput}
                onChangeText={text => setLastname(text)}
                value={lastname}
                placeholder="*Nachame"
            />
            <TextInput
                style={styles.textInput}
                onChangeText={text => setEmail(text)}
                value={email}
                placeholder="*E-Mail"
            />
            <TextInput
                style={styles.textInput}
                maxLength={15}
                secureTextEntry
                placeholder="*Password"
                onChangeText={password => setPassword(password)}
            />
            
            <TextInput
                style={styles.textInput}
                maxLength={15}
                secureTextEntry
                placeholder="*Password wiederholen"
                onChangeText={passwordConform => setPasswordConform(passwordConform)}
            />
            
            <View >
                <Text style={styles.text1}>{'Wählen Sie Ihren Geschlecht'}</Text>
            </View>
        
            <View >
              <Picker

                  //style={{ height: 20, width: 200, color: 'deepskyblue', }}
                  style={styles.button}
                  onValueChange={(itemValue) =>
                  setSex(itemValue)
                }>
                <Picker.Item label="Wählen Sie Ihren Geschlecht" value="null" />     
                <Picker.Item label="Man" value="M" />
                <Picker.Item label="Frau" value="W" />
                <Picker.Item label="Diverse" value="D" />

              </Picker>
              <View >
                <Text style={styles.text1}>{sex}</Text>
              </View>
            </View>
            <View>
              <View>
                <TouchableOpacity onPress= {showDatepicker} 
                style={styles.button}>
                <View>
                  <Text style={styles.text2}>* Fügen Sie Ihr Geburtsdatum ein</Text>
                </View>
                </TouchableOpacity>
             </View>
                {show && (
                  <DateTimePicker
                    testID="dateTimePicker"
                    value={date}
                    mode={mode}
                    is24Hour={true}
                    display="default"
                    onChange={onChange}
                  />
                )}
                <TouchableOpacity onPress={
                  () => {
                      if (nickName &&firstname && lastname && email && sex && password && passwordConform ) {
                          if(password == passwordConform){
                              saveUserDatas();
                          }else{
                              alert('Wiederholen Sie die Passworteingabe');
                          }
                      } else {
                        alert('Sie müßen alle markierte Felder Ausfühlen');
                        return;
                      }

                    }
                  } style={styles.button}>
                    <View>
                      <Text style={styles.text2}>{props.utext}</Text>
                    </View>
                </TouchableOpacity>
          </View>

        
        
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
        width: 30,
        height: 30
    },

    button: {
        color: 'white',
        fontSize: 16,
        fontWeight: "bold",
        backgroundColor: 'deepskyblue',
        alignItems: 'center',
        justifyContent: 'center',
        paddingHorizontal: 5,
        paddingVertical: 10,
        width: 400,
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
        width: 400,
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
      },
      choice: {
        padding: 5,
        marginVertical: 5,
        marginHorizontal: 16,
        textAlign: "center",
        fontSize: 22,
        color: 'black',
      },
});