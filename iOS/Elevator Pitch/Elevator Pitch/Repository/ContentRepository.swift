//
//  ContentRepository.swift
//  Elevator Pitch
//
//  Created by Yusuf on 5.11.2022.
//

import Foundation
import Get

final class ContentRepository {
    private let client = APIClient(baseURL: URL(string: "http://35.223.183.146/api"))
    private let store: TokenStore
    
    init(store: TokenStore = TokenStore.shared) {
        self.store = store
    }
    
    func getContent() async -> [Content] {
        let request = Request(path: "/elevator-pitches", method: .get, headers: ["Authorization": "Bearer " + (store.token ?? "")]).withResponse([Content].self)
        let response = try? await client.send(request).value
        return response ?? []
    }
    
    @discardableResult
    func like(with id: Int) async -> Bool {
        let request = Request(path: "elevator-pitches/\(id)/like", method: .put, headers: ["Authorization": "Bearer " + (store.token ?? "")]).withResponse(Bool.self)
        let response = try? await client.send(request).value
        return response ?? false
    }
    
    @discardableResult
    func unlike(with id: Int) async -> Bool {
        let request = Request(path: "elevator-pitches/\(id)/unlike", method: .put, headers: ["Authorization": "Bearer " + (store.token ?? "")]).withResponse(Bool.self)
        let response = try? await client.send(request).value
        return response ?? false
    }
}
