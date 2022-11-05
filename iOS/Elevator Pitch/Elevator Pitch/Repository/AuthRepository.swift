//
//  AuthRepository.swift
//  Elevator Pitch
//
//  Created by Yusuf on 5.11.2022.
//

import Foundation
import Get

final class AuthRepository {
    private let client = APIClient(baseURL: URL(string: "http://35.223.183.146/api"))
    private let store: TokenStore
    
    init(store: TokenStore = TokenStore.shared) {
        self.store = store
    }
    
    var isLogin: Bool {
        store.token != nil
    }
    
    func register(req: RegisterRequest) async -> String {
        let request = Request(path: "/register", method: .post, body: req)
        let _ = try? await client.send(request)
        let loginResponse = await login(req: .init(username: req.email, password: req.password))
        return loginResponse ?? ""
    }
    
    func login(req: AuthRequest) async -> String {
        let request = Request(path: "/authenticate", method: .post, body: req).withResponse(Auth.self)
        let response = try? await client.send(request).value
        return response?.id_token ?? ""
    }
    
    func logout() {
        removeToken()
    }
    
    func setToken(_ token: String) {
        store.token = token
    }
    
    private func removeToken() {
        store.token = nil
    }
}
