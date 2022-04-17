package net.accessory.paragram.services;

import net.accessory.paragram.entities.Token;

public interface TokenService {
    boolean isExist(String token);
    void insertTokenToBlacklist(Token token);
}
