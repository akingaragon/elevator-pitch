//
//  RegisterView.swift
//  Elevator Pitch
//
//  Created by Yusuf on 5.11.2022.
//

import SwiftUI

struct RegisterView: View {
    @ObservedObject var viewModel: RegisterViewModel
    
    init(viewModel: RegisterViewModel = RegisterViewModel()) {
        self.viewModel = viewModel
    }
    
    var body: some View {
        NavigationView {
            Form {
                TextField("Username", text: $viewModel.emailField)
                SecureField("Password", text: $viewModel.passwordField)
                Button {
                    Task {
                        await viewModel.register()
                    }
                } label: {
                    Text("Register")
                        .frame(maxWidth: .infinity)
                }
                .buttonStyle(.borderedProminent)
                
                Button {
                    Task {
                        viewModel.openLogin = true
                    }
                } label: {
                    Text("Login")
                        .frame(maxWidth: .infinity)
                }
                .buttonStyle(.borderedProminent)
            }
            .navigationTitle("Register")
        }
        .fullScreenCover(isPresented: $viewModel.openHome) {
            HomeView()
        }
        .fullScreenCover(isPresented: $viewModel.openLogin) {
            LoginView()
        }
    }
}

struct RegisterView_Previews: PreviewProvider {
    static var previews: some View {
        RegisterView()
    }
}
