//
//  Content.swift
//  Elevator Pitch
//
//  Created by Yusuf on 5.11.2022.
//

import Foundation

struct Content: Codable {
    let id: Int
    let title: String
    let description: String
    let videoUrl: String
    let thumbnailUrl: String
    var likeNumber: Int
    var liked: Bool
}
