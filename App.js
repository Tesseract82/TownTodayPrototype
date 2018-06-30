import React from 'react';
import { StyleSheet, Text, View } from 'react-native';
import { images } from './Images';

export default class App extends React.Component {
  render() {
    return (
        <Image source={images.nav_bottom_1} style={{width: 193, height: 110}}/>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
  },
});
