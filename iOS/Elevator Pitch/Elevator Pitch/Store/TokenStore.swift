//
//  TokenStore.swift
//  Elevator Pitch
//
//  Created by Yusuf on 5.11.2022.
//

import Foundation
import KeychainSwift

final class TokenStore {
    private let keychain = KeychainSwift()
    
    static let shared = TokenStore()
    
    enum Key: String {
        case token = "token"
    }

    var token: String? {
        set {
            guard let value = newValue else {
                keychain.delete(Key.token.rawValue)
                return
            }
            keychain.set(value, forKey: Key.token.rawValue)
        }
        get {
            keychain.get(Key.token.rawValue)
        }
    }
}
