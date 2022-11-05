//
//  LoginView.swift
//  Elevator Pitch
//
//  Created by Yusuf on 5.11.2022.
//

import SwiftUI

struct LoginView: View {
    @ObservedObject var viewModel: LoginViewModel
    
    init(viewModel: LoginViewModel = LoginViewModel()) {
        self.viewModel = viewModel
    }
    
    var body: some View {
        NavigationView {
            Form {
                TextField("Email", text: $viewModel.emailField)
                SecureField("Password", text: $viewModel.passwordField)
                Button {
                    Task {
                        await viewModel.login()
                    }
                } label: {
                    Text("Login")
                }
                .buttonStyle(.bordered)
                
                Button {
                    viewModel.openRegister = true
                } label: {
                    Text("Register")
                }
                .buttonStyle(.bordered)
            }
            .navigationTitle("Login")
        }
        .fullScreenCover(isPresented: $viewModel.openHome) {
            HomeView()
        }
        .fullScreenCover(isPresented: $viewModel.openRegister) {
            RegisterView()
        }
    }
}

struct LoginView_Previews: PreviewProvider {
    static var previews: some View {
        LoginView()
    }
}
