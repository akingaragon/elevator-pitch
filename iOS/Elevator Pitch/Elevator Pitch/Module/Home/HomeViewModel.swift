//
//  HomeViewModel.swift
//  Elevator Pitch
//
//  Created by Yusuf on 5.11.2022.
//

import Foundation

final class HomeViewModel: ObservableObject {
    private let contentRepository: ContentRepository
    private let authRepository: AuthRepository
    
    @Published var contents: [Content] = []
    @Published var isLoading: Bool = false
    @Published var showRegister: Bool = false
    
    init(contentRepository: ContentRepository = ContentRepository(), authRepository: AuthRepository = AuthRepository()) {
        self.contentRepository = contentRepository
        self.authRepository = authRepository
    }
    
    func getContent() async {
        updateLoading(with: true)

        let response = await contentRepository.getContent()
        
        DispatchQueue.main.async {
            self.contents = response
        }
        updateLoading(with: false)
    }
    
    func like(id: Int) async {
        if let isLiked = contents.first(where: {$0.id == id})?.liked {
            if isLiked {
                updateLoading(with: true)
                await contentRepository.unlike(with: id)
            } else {
                updateLoading(with: true)
                await contentRepository.like(with: id)
           }
            await getContent()
            updateLoading(with: false)
        }
    }
    
    func updateLoading(with isLoading: Bool) {
        DispatchQueue.main.async {
            self.isLoading = isLoading
        }
    }
    
    func logout() async {
        authRepository.logout()
        showRegister = true
    }
}
