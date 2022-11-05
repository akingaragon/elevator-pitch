//
//  RegisterViewModel.swift
//  Elevator Pitch
//
//  Created by Yusuf on 5.11.2022.
//

import Foundation

final class RegisterViewModel: ObservableObject {
    private let repository: AuthRepository
    
    @Published var emailField: String = ""
    @Published var passwordField: String = ""
    @Published var openLogin: Bool = false
    @Published var openHome: Bool = false
    
    init(repository: AuthRepository = AuthRepository()) {
        self.repository = repository
    }
    
    func register() async {
        if !emailField.isEmpty && !passwordField.isEmpty {
            let response = await repository.register(req: .init(login: emailField, email: emailField, password: passwordField))
            DispatchQueue.main.async {
                self.repository.setToken(response)
                self.openHome = true
            }
        }
    }
}
