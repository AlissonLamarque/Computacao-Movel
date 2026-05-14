import React, { useState } from 'react';
import {
  View,
  Text,
  TextInput,
  Button,
  ScrollView,
  StyleSheet,
  TouchableOpacity,
  Switch
} from 'react-native';

export default function App() {
  const [nome, setNome] = useState('');
  const [cpf, setCpf] = useState('');
  const [telefone, setTelefone] = useState('');
  const [endereco, setEndereco] = useState('');
  const [sexo, setSexo] = useState('');
  const [aceitouTermos, setAceitouTermos] = useState(false);

  const [contatos, setContatos] = useState([]);
  const [proximoId, setProximoId] = useState(1);

  function adicionarContato() {
    if (nome.trim() === '') return;
    if (!aceitouTermos) return;

    const novo = {
      id: proximoId,
      nome,
      cpf,
      telefone,
      endereco,
      sexo,
      aceitouTermos
    };

    setContatos(contatos.concat(novo));
    setProximoId(proximoId + 1);

    setNome('');
    setCpf('');
    setTelefone('');
    setEndereco('');
    setSexo('');
    setAceitouTermos(false);
  }

  return (
    <View style={styles.container}>
      <Text style={styles.titulo}>Lista de Contatos</Text>

      <TextInput
        style={styles.input}
        placeholder="Digite um nome"
        value={nome}
        onChangeText={setNome}
      />

      <TextInput
        style={styles.input}
        placeholder="Digite o CPF"
        value={cpf}
        onChangeText={setCpf}
      />

      <TextInput
        style={styles.input}
        placeholder="Digite o telefone"
        value={telefone}
        onChangeText={setTelefone}
      />

      <TextInput
        style={styles.input}
        placeholder="Digite o endereço"
        value={endereco}
        onChangeText={setEndereco}
      />

      <Text style={styles.label}>Sexo</Text>

      <View style={styles.radioContainer}>
        <TouchableOpacity
          style={styles.radioItem}
          onPress={() => setSexo('Masculino')}
        >
          <View style={styles.radioOuter}>
            {sexo === 'Masculino' && <View style={styles.radioInner} />}
          </View>
          <Text style={styles.radioText}>Masculino</Text>
        </TouchableOpacity>

        <TouchableOpacity
          style={styles.radioItem}
          onPress={() => setSexo('Feminino')}
        >
          <View style={styles.radioOuter}>
            {sexo === 'Feminino' && <View style={styles.radioInner} />}
          </View>
          <Text style={styles.radioText}>Feminino</Text>
        </TouchableOpacity>
      </View>

      <View style={styles.switchContainer}>
        <Text style={styles.switchLabel}>Aceitar termos (obrigatório)</Text>

        <Switch
          value={aceitouTermos}
          onValueChange={setAceitouTermos}
        />
      </View>

      <Button
        title="Adicionar"
        onPress={adicionarContato}
      />

      <ScrollView style={styles.lista}>
        {contatos.map(contato => (
          <View key={contato.id} style={styles.card}>
            <Text style={styles.nome}>
              {contato.id} - {contato.nome}
            </Text>

            <Text style={styles.info}>
              CPF: {contato.cpf}
            </Text>

            <Text style={styles.info}>
              Telefone: {contato.telefone}
            </Text>

            <Text style={styles.info}>
              Endereço: {contato.endereco}
            </Text>

            <Text style={styles.info}>
              Sexo: {contato.sexo}
            </Text>

            <Text style={styles.info}>
              Aceitou termos: {contato.aceitouTermos ? 'Sim' : 'Não'}
            </Text>
          </View>
        ))}
      </ScrollView>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    padding: 20,
    marginTop: 40
  },

  titulo: {
    fontSize: 24,
    marginBottom: 10
  },

  input: {
    borderWidth: 1,
    borderColor: '#ccc',
    padding: 10,
    marginBottom: 10
  },

  lista: {
    marginTop: 10
  },

  card: {
    backgroundColor: '#fff',
    padding: 15,
    marginBottom: 12,
    borderRadius: 10,
    shadowColor: '#000',
    shadowOffset: {
      width: 0,
      height: 2
    },
    shadowOpacity: 0.15,
    shadowRadius: 4,
    elevation: 4
  },

  nome: {
    fontSize: 20,
    fontWeight: 'bold',
    marginBottom: 8
  },

  info: {
    fontSize: 16,
    marginBottom: 4
  },

  label: {
    fontSize: 16,
    marginBottom: 8,
    marginTop: 5
  },

  radioContainer: {
    marginBottom: 15
  },

  radioItem: {
    flexDirection: 'row',
    alignItems: 'center',
    marginBottom: 10
  },

  radioOuter: {
    width: 22,
    height: 22,
    borderRadius: 11,
    borderWidth: 2,
    borderColor: '#555',
    justifyContent: 'center',
    alignItems: 'center',
    marginRight: 10
  },

  radioInner: {
    width: 10,
    height: 10,
    borderRadius: 5,
    backgroundColor: '#2196f3'
  },

  radioText: {
    fontSize: 16
  },

  switchContainer: {
    flexDirection: 'row',
    alignItems: 'center',
    justifyContent: 'space-between',
    marginBottom: 15
  },

  switchLabel: {
    fontSize: 16
  }
});