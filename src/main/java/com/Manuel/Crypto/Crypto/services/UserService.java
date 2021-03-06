package com.Manuel.Crypto.Crypto.services;


import com.Manuel.Crypto.Crypto.exceptions.RecordNotFoundException;
import com.Manuel.Crypto.Crypto.model.Usuario;
import com.Manuel.Crypto.Crypto.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class UserService {
    @Autowired

    UsuarioRepository repository;


    //devuelve todos los usuarios
    public List<Usuario> getAllUsers()
    {
        List<Usuario> UserList = repository.findAll();

        if(UserList.size() > 0) {
            return UserList;
        } else {
            return new ArrayList<Usuario>();
        }
    }

//devuelve un usuario por id

    public Usuario getUserById(String id) throws RecordNotFoundException {
        Optional<Usuario> user = repository.findById(id);

        if(user.isPresent()) {
            return user.get();
        } else {
            throw new RecordNotFoundException("No item record exist for given id",id);
        }
    }

    //inserta un usuario en la base de datos
    public Usuario createUser(Usuario entity){
        entity = repository.save(entity);
        return entity;
    }

    //actualiza un usuario(name, foto, bio , y publicaciones)
    public Usuario UpdateUser(Usuario entity) throws RecordNotFoundException
    {

        Optional<Usuario> item = repository.findById(entity.getId());

        if(item.isPresent())
        {
            Usuario newEntity = item.get();
            //newEntity.setId(entity.getId());
            newEntity.setName(entity.getName());
            newEntity.setSurname(entity.getSurname());
            newEntity.setEmail(entity.getEmail());
            newEntity.setEmail(entity.getEmail());





            newEntity = repository.save(newEntity);

            return newEntity;
        } else {
            throw new RecordNotFoundException("Item not found",entity.getId());
        }
    }
    //Borra un usuario por id
    public void deleteUserById(String id) throws RecordNotFoundException
    {
        Optional<Usuario> item = repository.findById(id);

        if(item.isPresent())
        {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No item record exist for given id",id);
        }
    }


    //filtra los usuario por nombre
    public List<Usuario> getUserByname(String name) {
        List<Usuario> itemList = repository.getByName(name);

        if(itemList.size() > 0) {
            return itemList;
        } else {
            return new ArrayList<Usuario>();
        }
    }

    //filtra los usuario por nombre
    public List<Usuario> getUserBycash() {
        List<Usuario> itemList = repository.getByCash();

        if(itemList.size() > 0) {
            return itemList;
        } else {
            return new ArrayList<Usuario>();
        }
    }
}