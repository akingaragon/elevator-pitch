//
//  HomeItemView.swift
//  Elevator Pitch
//
//  Created by Yusuf on 5.11.2022.
//

import SwiftUI
import AVFoundation
import VideoPlayer

struct HomeItemView: View {
    @State var player: AVPlayer
    @Binding var content: Content
    @State var showVideo: Bool = false
    var action: (Int) -> Void
    
    init(content: Binding<Content>, action: @escaping (Int) -> Void) {
        let item = AVPlayerItem(url: URL(string: content.wrappedValue.videoUrl)!)
        item.externalMetadata = [AVMetadataItem()]
        self.player = AVPlayer(playerItem: item)
        self._content = content
        self.action = action
    }
    
    var body: some View {
        VStack(alignment: .leading) {
            if showVideo {
                VideoPlayer(player: player)
                    .frame(height: 224.0)
                    .onAppear {
                        player.play()
                    }
                    .onDisappear {
                        player.pause()
                    }
            } else {
                Button {
                    showVideo = true
                } label: {
                    if let url = URL(string: content.thumbnailUrl) {
                        AsyncImage(
                            url: url,
                            content: { image in
                                image
                                    .resizable()
                                    .scaledToFill()
                                    .overlay {
                                        Image("play_icon")
                                    }
                                    .frame(height: 224.0)
                            },
                            placeholder: {
                                VStack {
                                    Spacer()
                                    ProgressView()
                                        .frame(maxWidth: .infinity)
                                    Spacer()
                                }
                                .frame(height: 224.0)
                            }
                        )
                    } else {
                        EmptyView()
                    }
                }
            }
            VStack(alignment: .leading, spacing: 16.0) {
                HStack {
                    Text(content.title).font(.headline).fontWeight(.semibold).foregroundColor(.white)
                        .frame(alignment: .leading)
                    Button {
                        action(content.id)
                    } label: {
                        Image(content.liked ? "like_icon" : "unlike_icon")
                    }
                }
                Text(content.description).font(.body).fontWeight(.light).foregroundColor(.white)
                    .multilineTextAlignment(.leading)
                    .frame(alignment: .leading)
            }
            .padding(.all, 16)
        }
        .frame(height: 375.0)
    }
}
