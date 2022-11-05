//
//  Auth.swift
//  Elevator Pitch
//
//  Created by Yusuf on 5.11.2022.
//

import Foundation

struct Auth: Codable {
    let id_token: String
}

struct AuthRequest: Codable {
    let username: String
    let password: String
}

struct RegisterRequest: Codable {
    let login: String
    let firstName: String = "test"
    let lastName: String = "test"
    let email: String
    let activated: Bool = true
    let createdBy: String = "string"
    let createdDate: String = "2022-11-05T15:44:39.008Z"
    let authorities: [String] = ["ROLE_INVENTOR"]
    let lastModifiedBy: String = "string"
    let lastModifiedDate: String = "2022-11-05T15:44:39.008Z"
    let password: String
}
