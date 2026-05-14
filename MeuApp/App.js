import React, { useState } from 'react';
import {
  View,
  Text,
  TextInput,
  Button,
  ScrollView,
  StyleSheet
} from 'react-native';

export default function App() {
  // 1. Estado para texto digitado
  const [nome, setNome] = useState('');
  const [cpf, setCpf] = useState('');
  const [telefone, setTelefone] = useState('');
  const [endereco, setEndereco] = useState('');
  // 2. Estado para lista de contatos
  const [contatos, setContatos] = useState([]);
  // 3. Estado para gerar IDs sequenciais
  const [proximoId, setProximoId] = useState(1);

  // 4. Função acionada pelo botão
  function adicionarContato() {
    if (nome.trim() === '') return;      // 4.1. Se vazio, não faz nada
    const novo = { id: proximoId, nome, cpf, telefone, endereco }; // 4.2. Cria objeto contato
    setContatos(contatos.concat(novo));   // 4.3. Adiciona ao array
    setProximoId(proximoId + 1);          // 4.4. Incrementa ID
    setNome('');                          // 4.5. Limpa o campo
    setCpf('');                           // 4.6. Limpa o campo
    setTelefone('');                       // 4.7. Limpa o campo
    setEndereco('');                       // 4.8. Limpa o campo
  }

  return (
    <View style={styles.container}>
      {/* 5. Título da aplicação */}
      <Text style={styles.titulo}>Lista de Contatos</Text>

      {/* 6. Campo de entrada de texto */}
      <TextInput
        style={styles.input}
        placeholder="Digite um nome"
        value={nome}
        onChangeText={texto => setNome(texto)}
      />
      <TextInput
        style={styles.input}
        placeholder="Digite o CPF"
        value={cpf}
        onChangeText={texto => setCpf(texto)}
      />
      <TextInput
        style={styles.input}
        placeholder="Digite o telefone"
        value={telefone}
        onChangeText={texto => setTelefone(texto)}
      />
      <TextInput
        style={styles.input}
        placeholder="Digite o endereço"
        value={endereco}
        onChangeText={texto => setEndereco(texto)}
      />
      {/* 7. Botão para adicionar contato */}
      <Button
        title="Adicionar"
        onPress={adicionarContato}
      />

      {/* 8. Lista rolável de contatos */}
      <ScrollView style={styles.lista}>
        {contatos.map(contato => (
          <View key={contato.id} style={styles.card}> {/* Agrupa os detalhes de cada contato */}
            <Text key={contato.id} style={styles.nome}>  {/*/ cada item precisa de chave única para o React rastrear as atualizações*/}
              {/* 8.1. Exibe ID e nome */}
              {contato.id} - {contato.nome}
            </Text>
            <Text key={contato.cpf} style={styles.info}>
              CPF: {contato.cpf}
            </Text>
            <Text key={contato.telefone} style={styles.info}>
              Telefone: {contato.telefone}
            </Text>
            <Text key={contato.endereco} style={styles.info}>
              Endereço: {contato.endereco}
            </Text>
        </View>
      ))}
      </ScrollView>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,          // ocupa tela inteira
    padding: 20,      // espaçamento interno
    marginTop: 40     // afasta da status bar
  },
  titulo: {
    fontSize: 24,     // tamanho do título
    marginBottom: 10  // espaço abaixo
  },
  input: {
    borderWidth: 1,   // borda fina
    borderColor: '#ccc',
    padding: 10,
    marginBottom: 10
  },
  lista: {
    marginTop: 10     // espaço antes da lista
  },
  item: {
    fontSize: 18,     // tamanho do texto dos itens
    marginVertical: 5 // espaço entre itens
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
}
});