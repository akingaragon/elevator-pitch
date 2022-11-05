//
//  LoginViewModel.swift
//  Elevator Pitch
//
//  Created by Yusuf on 5.11.2022.
//

import Foundation

final class LoginViewModel: ObservableObject {
    private let repository: AuthRepository
    
    @Published var emailField: String = ""
    @Published var passwordField: String = ""
    @Published var openRegister: Bool = false
    @Published var openHome: Bool = false
    
    init(repository: AuthRepository = AuthRepository()) {
        self.repository = repository
    }
    
    func login() async {
        if !emailField.isEmpty && !passwordField.isEmpty {
            let response = await repository.login(req: .init(username: emailField, password: passwordField))
            repository.setToken(response)
            openHome = true
        }
    }
}
