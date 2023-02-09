/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow strict-local
 */

import React from 'react';

import {
  Button,
  SafeAreaView,
  StyleSheet,
} from 'react-native';

import {
  Colors,
} from 'react-native/Libraries/NewAppScreen';

import { NativeModules } from 'react-native';

const App = () => {
  const { BeHealthyModule } = NativeModules;

  const backgroundStyle = {
    backgroundColor: Colors.lighter,
    justifyContent: 'center',
    alignItems: 'center',
    flex: 1
  };

  const handlePress = () => {
    console.log("init behealthy")
    BeHealthyModule.testBeHealthy();
  }

  return (
    <SafeAreaView style={backgroundStyle}>
      <Button title='TEST BEHEALTHY' onPress={handlePress} />
    </SafeAreaView>
  );
};

const styles = StyleSheet.create({
  sectionContainer: {
    marginTop: 32,
    paddingHorizontal: 24,
  },
  sectionTitle: {
    fontSize: 24,
    fontWeight: '600',
  },
  sectionDescription: {
    marginTop: 8,
    fontSize: 18,
    fontWeight: '400',
  },
  highlight: {
    fontWeight: '700',
  },
});


export default App;
