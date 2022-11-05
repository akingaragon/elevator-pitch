//
//  HomeView.swift
//  Elevator Pitch
//
//  Created by Yusuf on 5.11.2022.
//

import SwiftUI

struct HomeView: View {
    @ObservedObject var viewModel: HomeViewModel
    
    init(viewModel: HomeViewModel = HomeViewModel()) {
        self.viewModel = viewModel
    }
    
    var body: some View {
        NavigationView {
            ScrollView(.vertical) {
                ForEach($viewModel.contents, id: \.id) { $content in
                    HomeItemView(content: $content) { id in
                        Task {
                            await viewModel.like(id: id)
                        }
                    }
                }
            }
            .navigationTitle("Home")
            .navigationBarTitleDisplayMode(.inline)
            .toolbar {
                ToolbarItem(placement: .navigationBarTrailing) {
                    Button {
                        Task {
                            await viewModel.logout()
                        }
                    } label: {
                        Text("Logout")
                    }
                }
            }
            .allowsHitTesting(!viewModel.isLoading)
            .overlay {
                if viewModel.isLoading {
                    ProgressView()
                        .foregroundColor(.white)
                }
            }
        }
        .onAppear {
            Task {
                await viewModel.getContent()
            }
        }
        .fullScreenCover(isPresented: $viewModel.showRegister) {
            RegisterView()
        }
    }
}

struct HomeView_Previews: PreviewProvider {
    static var previews: some View {
        HomeView()
    }
}
