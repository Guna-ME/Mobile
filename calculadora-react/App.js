import React, { useState } from 'react';
import { StyleSheet, View, Text, TouchableOpacity } from 'react-native';

const App = () => {
  const [input, setInput] = useState('');
  const [result, setResult] = useState('');

  const handlePress = (value) => {
    setInput((prev) => prev + value);
  };

  const calculate = () => {
    try {
      const evalResult = eval(input); // Use com cuidado em produção
      setResult(evalResult.toString());
      setInput('');
    } catch (error) {
      setResult('Erro');
    }
  };

  const clear = () => {
    setInput('');
    setResult('');
  };

  return (
    <View style={styles.container}>
      <View style={styles.display}>
        <Text style={styles.input}>{input}</Text>
        <Text style={styles.result}>{result}</Text>
      </View>
      <View style={styles.operationContainer}>
        {['+', '-', '*', '/'].map((op) => (
          <TouchableOpacity
            key={op}
            style={styles.operationButton}
            onPress={() => handlePress(op)}>
            <Text style={styles.buttonText}>{op}</Text>
          </TouchableOpacity>
        ))}
      </View>
      <View style={styles.buttonContainer}>
        <View style={styles.row}>
          {['1', '2', '3'].map((button) => (
            <TouchableOpacity
              key={button}
              style={styles.button}
              onPress={() => handlePress(button)}>
              <Text style={styles.buttonText}>{button}</Text>
            </TouchableOpacity>
          ))}
        </View>
        <View style={styles.row}>
          {['4', '5', '6'].map((button) => (
            <TouchableOpacity
              key={button}
              style={styles.button}
              onPress={() => handlePress(button)}>
              <Text style={styles.buttonText}>{button}</Text>
            </TouchableOpacity>
          ))}
        </View>
        <View style={styles.row}>
          {['7', '8', '9'].map((button) => (
            <TouchableOpacity
              key={button}
              style={styles.button}
              onPress={() => handlePress(button)}>
              <Text style={styles.buttonText}>{button}</Text>
            </TouchableOpacity>
          ))}
        </View>
        <View style={styles.row}>
          <TouchableOpacity
            style={styles.button}
            onPress={() => handlePress('0')}>
            <Text style={styles.buttonText}>0</Text>
          </TouchableOpacity>
          <TouchableOpacity
            style={styles.button}
            onPress={clear}>
            <Text style={styles.buttonText}>C</Text>
          </TouchableOpacity>
          <TouchableOpacity
            style={[styles.button, styles.equalButton]}
            onPress={calculate}>
            <Text style={styles.buttonText}>=</Text>
          </TouchableOpacity>
        </View>
      </View>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    padding: 20,
    backgroundColor: '#f5f5f5',
  },
  display: {
    flex: 1,
    justifyContent: 'flex-end',
    alignItems: 'flex-end',
    padding: 20,
    backgroundColor: '#fff',
    borderRadius: 10,
    elevation: 5,
  },
  input: {
    fontSize: 30,
    color: '#000',
  },
  result: {
    fontSize: 24,
    color: '#888',
  },
  operationContainer: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    marginBottom: 10,
  },
  operationButton: {
    width: '22%',
    height: 60,
    backgroundColor: '#d1c4e9',
    justifyContent: 'center',
    alignItems: 'center',
    borderRadius: 30,
  },
  buttonContainer: {
    flexDirection: 'column',
    justifyContent: 'space-between',
  },
  row: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    marginBottom: 10,
  },
  button: {
    width: '30%', // Ajustado para caber melhor
    height: 60,
    backgroundColor: '#d1c4e9',
    justifyContent: 'center',
    alignItems: 'center',
    borderRadius: 30,
    margin: 5,
  },
  equalButton: {
    width: '30%', // Ocupar dois campos
    backgroundColor: '#6a1b9a', // Cor diferente para o botão =
  },
  buttonText: {
    fontSize: 24,
    color: '#fff',
  },
});

export default App;
