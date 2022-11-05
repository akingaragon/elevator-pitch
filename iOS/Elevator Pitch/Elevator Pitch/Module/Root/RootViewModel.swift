//
//  RootViewModel.swift
//  Elevator Pitch
//
//  Created by Yusuf on 5.11.2022.
//

import Foundation

final class RootViewModel: ObservableObject {
    private let repository: AuthRepository
    
    init(repository: AuthRepository = AuthRepository()) {
        self.repository = repository
    }
    
    var isLogin: Bool {
        repository.isLogin
    }
}
